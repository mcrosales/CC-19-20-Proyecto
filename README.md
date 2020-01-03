# CC-19-20-Proyecto [![Build Status](https://travis-ci.org/mcrosales/CC-19-20-Proyecto.svg?branch=master)](https://travis-ci.org/mcrosales/CC-19-20-Proyecto) [![CircleCI](https://circleci.com/gh/mcrosales/CC-19-20-Proyecto.svg?style=svg)](https://circleci.com/gh/mcrosales/CC-19-20-Proyecto)

Repositorio para proyecto de asignatura Cloud Computing de Maestría  en Informática de la Universidad de Granada

## Mayorista

La aplicación Mayorista constará de dos entidades fundamentales. De una parte, permitirá a potenciales vendedores mayoristas la gestión centralizada de todos los productos que tienen en venta.
De otra, se ofrecerán estadísticas centralizadas, disponibles para los propios vendedores o terceros, previo acuerdo. 

### Arquitectura
La arquitectura del proyecto se encuentra detallada en el siguiente [enlace](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/hitos/README_Hito_1.md)

#### Microservicios

Ambos microservicios siguen la siguiente estructura de paquetes:

+ controller: contiene los endpoint de la aplicación
+ service: lógica de negocio
+ repository: implementación de acceso a datos 
+ model: entidades del negocio 

##### Versiones de lenguaje y herramientas

El proyecto hace uso extensivo de [Spring Boot](https://github.com/spring-projects/spring-boot) y por ende del lenguaje Java. La versión estable más reciente de Spring Boot es la 2.2.2. Soporta las versiones de Java 8, 11 y 13. A pesar de ser Java 8 la más antigua se seleccionó por su madurez y su alta compatibilidad con otras herramientas del ecosistema.

##### Herramienta de construcción

buildtool: pom.xml

Al ser un proyecto Java, en cuanto a herramientas de construcción se refiere dos de las más utilizadas son
[Maven](https://maven.apache.org/) y [Gradle](https://gradle.org/). El proyecto utiliza Maven, dada la amplia documentación existente y la posibilidad de manejar las dependencias de manera centralizada a través del fichero pom.xml

Mediante un conjunto de instrucciones tales como *clean*, *validate*, *test* y package, maven descarga las dependencias, corre los tests y empaqueta toda la aplicación en un archivo .jar

##### Pruebas

Al inicializar un proyecto [Spring Boot](https://start.spring.io/) se pueden seleccionar un conjunto de dependencias en función de las necesidades del proyecto. Al seleccionar Spring Web se añaden las dependencias necesarias para desarrollo Restful asi como la configuración automática de un servidor [Tomcat](http://tomcat.apache.org/) embebido. Adicionalmente, en el pom.xml se lista la dependencia *spring-boot-starter-test*. Esta dependencia incluye lo siguiente:

+ [JUnit](https://junit.org/junit5/): *framework* de pruebas para Java en su versión 5
+ [Mockito](https://site.mockito.org/): *mocking framework* de pruebas unitarias, en este caso la versión 3
+ [AssertJ](https://assertj.github.io/doc/): librería de aserciones, versión 3
+ [Hamcrest](http://hamcrest.org/JavaHamcrest/): *framework* de emparejadores (*matchers*) flexible, versión 2 

Las pruebas del proyecto se implementan utilizando este conjunto de componentes, aprovechando su compatibilidad con Spring Boot.

##### Integración continua

La primera opción considerada fue Jenkins, pero no fue posible encontrar una web gratuita para utilizar. En esta fase del proyecto se configuraron dos entornos diferentes para evaluar, uno con [Travis](https://travis-ci.org/) y otro con [CircleCI](https://circleci.com/). Cada uno tiene fortalezas y debilidades, por ejemplo en el plan gratuito Travis permite ejecutar los test en más de una máquina a la vez. Ambos se configuran con archivos yml, Travis con [.travis.yml](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/.travis.yml) y CircleCi con [.circleci/config.yml](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/.circleci/config.yml)


### Licencia
[GNU General Public License v3.0](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/LICENSE) 