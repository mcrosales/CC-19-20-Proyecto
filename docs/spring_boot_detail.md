# Spring Boot

Spring Boot es un proyecto que hace uso del ecosistema Spring para proveer un entorno de desarrollo en Java rápidamente desplegable con el mínimo de configuración pero con capacidad para escalar según se sumen requisitos a la aplicación. Según su sitio oficial:

"Facilita la creación de aplicaciones y servicios listos para producción impulsados por Spring con un mínimo de esfuerzo. Se pueden crear aplicaciones Java independientes, iniciables usando java -jar o utilizar empaquetado en archivos WAR."

Para crear un proyecto Spring Boot, una de las formas más expeditas es utilizar [Spring Initializr](https://start.spring.io/)

![](img\springInitializer.jpg)

En esta web se escogen las dependencias que necesitamos en dependencia del tipo de proyecto. Para este caso seleccionamos solamente Spring Web para tener lo mínimo.

Al descargar y descomprimir el archivo tenemos una carpeta con una estructura clásica de un proyecto maven, lo cual incluye los directorios src y test. En el pom.xml tenemos las dependencias escogidas (spring-boot-starter-web), las dependencias asociadas a pruebas (spring-boot-starter-test), la versión de java a utilizar y el plugin específico de Spring Boot para Maven (org.springframework.boot).

        <properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

La única clase creada, bajo el paquete src, es la que contiene el método *main*, incluyendo la anotación @SpringBootApplication. En el caso del microservecio *vendors_products* es la siguiente:

    package ugr.cc.vendors_products;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    public class VendorsProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorsProductsApplication.class, args);
	}

}

Con este tenemos una aplicación Java completamente funcional. Al ejecutar Run desde nuestro IDE preferido o empaquetando con Maven y ejecutando el JAR obtenemos una salida de consola similar a esta:

![](img\spring_boot.jpg)

Con esta salida, la aplicación se encuentra corriendo en un servidor Tomcat embebido autoconfigurado por Spring Boot en el puerto por defecto 8080.

### Bibliografía específica (tanto para consulta como para copia de instrucciones, de las cuales no pretendo por ningún motivo atribuirme su creación)

[Maven sitio oficial](https://maven.apache.org/)



