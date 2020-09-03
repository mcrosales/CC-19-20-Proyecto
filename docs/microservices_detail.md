# Microservicios

## Controladores REST

Para acceder a a las funcionalidades de nuestros microservicios es necesario exponer las funcionalidades
de alguna manera. La aplicaciones creadas con Spring Boot permiten exponer los servicios mediante
el empleo de la anotación @RestController. Cualquier clase con esta anotación será detectada automáticamente
y expuesta luego del camino base de la apliación. Este camino base se define en el fichero application.properties
de la manera siguiente:

`server.servlet.context-path=/vendors_products`

Por ejemplo, para acceder a las funcionalidades de "productos", el camino sería:

`/vendors_products/products/nombre_del_método/`

ya que en el controlador de productos se define su acceso con la anotación 

´@RequestMapping(value = "/products")´

Cada funcionalidad concreta se define con otras anotaciones, por ejemplo:

 ```
	/**
     * POST  /create -> Creates a new product.
     */
    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Product> create(@RequestBody Product product) throws URISyntaxException {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status", "OK");
        Product created = productService.saveProduct(product);
        return ResponseEntity.created(new URI("/products/" + created.getId()))
                .headers(responseHeaders)
                .body(product);
    }
 ```
 
Con la anotación @RequestMapping se define el camino (/create), el método utilizado para la petición
(method = RequestMethod.POST) y el formato de la respuesta (MediaType.APPLICATION_JSON_VALUE).

Adicionalmente se define que el método recibe en el cuerpo de la petición un objeto de tipo Product. Tanto los cuerpos
de las peticiones POST/PUT como las respuestas se serializan y deserializan usando la librería [Jackson](https://github.com/FasterXML/jackson) 

Si bien la creación de recursos se puede llevar a cabo a través de métodos [PUT o POST](http://restcookbook.com/HTTP%20Methods/put-vs-post/), se 
optó por separar la creación y la modificación a través de direcciones diferentes bajo /create con POST y /update con PUT, dado que bajo /products podemos
tener otros métodos en lo adelante.

## Inyección de dependencias

La inyección de dependencias es una técnica en la que un objeto recibe otros objetos de los que depende. 
Estos otros objetos se denominan dependencias. Típicamente, el objeto receptor se denomina cliente y el objeto
pasado ("inyectado") se denomina servicio. El código que pasa el servicio al cliente recibe el nombre 
de inyector. La "inyección" se refiere al paso de una dependencia (un servicio) al objeto (un cliente) que lo usaría.

En Spring es posible utilizar la inyección de dependencias por ejemplo a través del constructor de una clase.
Este comportamiento se emplea de la manera siguiente:

	@Service
	public class SiteStatisticsService {

    private final StatsRepository statsRepository;

    public SiteStatisticsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }
	
SiteStatisticsService define que necesita una implementación **final** de StatsRepository al construirse.
Con la anotación @Service al arrancar la apliación Spring Boot captura la clase y le inyecta
la dependencia necesaria, en este caso la implementación concreta del repositorio en dependencia
de la BD escogida (ver Persistencia)

## Persistencia

Si bien el almacenamiento de los datos de los microservicios no está incorporado (inicialmente se diseño Postgres y MongoDB para cada uno), Spring Boot permite
configurar una BD en memoria con [H2](https://www.h2database.com/html/main.html). Aunque en un entorno
de producción es más factible contar otro tipo de almacenamiento, H2 nos permite probar configuraciones y funcionamiento
de una manera flexible. Para indicar a Spring Boot que use H2 basta con añadir la dependencia en el pom.xml:

		
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

Cómo las clases encargadas de la recuperación y acceso a la información en el paquete repository heredan de
una interfaz común PagingAndSortingRepository, los métodos implementados deben funcionar de la misma
manera en cualquier gestor de BD relacional soportado (PostgreSQL, MySQL, H2, etc). 

Las clases que representan las tablas de la BD se agrupan en el paquete model. Cada una cuenta
con diferentes anotaciones de la API de persistencia de Java ([JPA](https://www.oracle.com/java/technologies/persistence-jsp.html))
Por ejemplo la anotación @Entity denota que la clase en cuestión será una entidad persistente en BD.

En tiempo de ejecución, Spring Boot se encarga de cargar una implementación concreta de JPA, por defecto
[Hibernate](https://hibernate.org/), para el tipo de BD escogido. Las interfaces que heredan de interfaces
relacionadas con JPA (cómo CrudRepository, PagingAndSortingRepository) permiten implementar métodos de manera 
"declarativa". Por ejemplo esta interfaz:

			public interface StatsRepository extends PagingAndSortingRepository<SiteStatistics, Integer> {

					List<SiteStatistics> findAllByOrderById();

					List<SiteStatistics> findAllByMeasurementDayGreaterThanEqualOrderById(Date date); 
					
 al heredar de PagingAndSortingRepository e incluir la clase SiteStatistics cuenta con métodos save, findAll y count implementados de manera automática
 para el acceso a la tabla SiteStatistics de la BD. Adicionalmente los métodos se pueden extender de manera declarativa, por ejemplo
 obteniendo sólo las estadísticas correspondientes a un rango de fecha.