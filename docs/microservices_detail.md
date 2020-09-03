# Microservicios

## Controladores REST

Para acceder a a las funcionalidades de nuestros microservicios es necesario exponer las funcionalidades
de alguna manera. La aplicaciones creadas con Spring Boot permiten exponer los servicios mediante
el empleo de la anotaci�n @RestController. Cualquier clase con esta anotaci�n ser� detectada autom�ticamente
y expuesta luego del camino base de la apliaci�n. Este camino base se define en el fichero application.properties
de la manera siguiente:

`server.servlet.context-path=/vendors_products`

Por ejemplo, para acceder a las funcionalidades de "productos", el camino ser�a:

`/vendors_products/products/nombre_del_m�todo/`

ya que en el controlador de productos se define su acceso con la anotaci�n 

�@RequestMapping(value = "/products")�

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
 
Con la anotaci�n @RequestMapping se define el camino (/create), el m�todo utilizado para la petici�n
(method = RequestMethod.POST) y el formato de la respuesta (MediaType.APPLICATION_JSON_VALUE).

Adicionalmente se define que el m�todo recibe en el cuerpo de la petici�n un objeto de tipo Product. Tanto los cuerpos
de las peticiones POST/PUT como las respuestas se serializan y deserializan usando la librer�a [Jackson](https://github.com/FasterXML/jackson) 

Si bien la creaci�n de recursos se puede llevar a cabo a trav�s de m�todos [PUT o POST](http://restcookbook.com/HTTP%20Methods/put-vs-post/), se 
opt� por separar la creaci�n y la modificaci�n a trav�s de direcciones diferentes bajo /create con POST y /update con PUT, dado que bajo /products podemos
tener otros m�todos en lo adelante.

## Inyecci�n de dependencias

La inyecci�n de dependencias es una t�cnica en la que un objeto recibe otros objetos de los que depende. 
Estos otros objetos se denominan dependencias. T�picamente, el objeto receptor se denomina cliente y el objeto
pasado ("inyectado") se denomina servicio. El c�digo que pasa el servicio al cliente recibe el nombre 
de inyector. La "inyecci�n" se refiere al paso de una dependencia (un servicio) al objeto (un cliente) que lo usar�a.

En Spring es posible utilizar la inyecci�n de dependencias por ejemplo a trav�s del constructor de una clase.
Este comportamiento se emplea de la manera siguiente:

	@Service
	public class SiteStatisticsService {

    private final StatsRepository statsRepository;

    public SiteStatisticsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }
	
SiteStatisticsService define que necesita una implementaci�n **final** de StatsRepository al construirse.
Con la anotaci�n @Service al arrancar la apliaci�n Spring Boot captura la clase y le inyecta
la dependencia necesaria, en este caso la implementaci�n concreta del repositorio en dependencia
de la BD escogida (ver Persistencia)

## Persistencia

Si bien el almacenamiento de los datos de los microservicios no est� incorporado (inicialmente se dise�o Postgres y MongoDB para cada uno), Spring Boot permite
configurar una BD en memoria con [H2](https://www.h2database.com/html/main.html). Aunque en un entorno
de producci�n es m�s factible contar otro tipo de almacenamiento, H2 nos permite probar configuraciones y funcionamiento
de una manera flexible. Para indicar a Spring Boot que use H2 basta con a�adir la dependencia en el pom.xml:

		
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

C�mo las clases encargadas de la recuperaci�n y acceso a la informaci�n en el paquete repository heredan de
una interfaz com�n PagingAndSortingRepository, los m�todos implementados deben funcionar de la misma
manera en cualquier gestor de BD relacional soportado (PostgreSQL, MySQL, H2, etc). 

Las clases que representan las tablas de la BD se agrupan en el paquete model. Cada una cuenta
con diferentes anotaciones de la API de persistencia de Java ([JPA](https://www.oracle.com/java/technologies/persistence-jsp.html))
Por ejemplo la anotaci�n @Entity denota que la clase en cuesti�n ser� una entidad persistente en BD.

En tiempo de ejecuci�n, Spring Boot se encarga de cargar una implementaci�n concreta de JPA, por defecto
[Hibernate](https://hibernate.org/), para el tipo de BD escogido. Las interfaces que heredan de interfaces
relacionadas con JPA (c�mo CrudRepository, PagingAndSortingRepository) permiten implementar m�todos de manera 
"declarativa". Por ejemplo esta interfaz:

			public interface StatsRepository extends PagingAndSortingRepository<SiteStatistics, Integer> {

					List<SiteStatistics> findAllByOrderById();

					List<SiteStatistics> findAllByMeasurementDayGreaterThanEqualOrderById(Date date); 
					
 al heredar de PagingAndSortingRepository e incluir la clase SiteStatistics cuenta con m�todos save, findAll y count implementados de manera autom�tica
 para el acceso a la tabla SiteStatistics de la BD. Adicionalmente los m�todos se pueden extender de manera declarativa, por ejemplo
 obteniendo s�lo las estad�sticas correspondientes a un rango de fecha.