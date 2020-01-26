# Maven
Es una herramienta de construcción de [Apache](https://maven.apache.org/) la cual se basa en un documento POM para gestionar la construcción, testeo y documentación de un proyecto. Un fichero POM puede ser similar al siguiente (no necesitamos todas las etiquetas siempre):

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
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
				<artifactId>spring-boot-maven-plugin
                </artifactId>
			</plugin>
		</plugins>
	</build>

Este fichero puede ser tan extenso y complicado como necesitemos sin embargo, varias secciones son comunes:

+ dependencies: 
Bajo esta etiqueta se listan todas las librerías externas necesarias para compilar el proyecto. Algunas dependencias pueden contener otras, convirtiendolas en dependecias transitivas.

+ plugins:
Bajo esta etiqueta se listan los plugins del proyecto que se van a ejecutar para realizar tareas concretas (generar documentación, reporte de covertura de código) En este caso tenemos listado *spring-boot-maven-plugin* que define tareas especifícas para una aplicación [Spring Boot](https://docs.spring.io/spring-boot/docs/current/maven-plugin/usage.html)

El ciclo de vida de maven se divide en fases, las cuales hacen uso de distintos plugins, ya sea definidos en el pom o propios por defecto de maven. Entre las fases podemos encontrar:


+ package: compila el código y lo empaqueta en el formato definido, típicamente WAR o JAR. Uso:

     *mvn package*

+ test: ejecuta las pruebas unitarias definidas. Uso:

    *mvn test*


### Bibliografía específica (tanto para consulta como para copia de instrucciones, de las cuales no pretendo por ningún motivo atribuirme su creación)

[Maven sitio oficial](https://maven.apache.org/)



