# CC-19-20-Proyecto [![Build Status](https://travis-ci.org/mcrosales/CC-19-20-Proyecto.svg?branch=master)](https://travis-ci.org/mcrosales/CC-19-20-Proyecto) [![CircleCI](https://circleci.com/gh/mcrosales/CC-19-20-Proyecto.svg?style=svg)](https://circleci.com/gh/mcrosales/CC-19-20-Proyecto)

Repositorio para proyecto de asignatura Cloud Computing de Maestría  en Informática de la Universidad de Granada

Versiones del README:

+[1](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/milestones/README_1.md)
+[2](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/milestones/README_2.md)

## Mayorista 

La aplicación Mayorista constará de dos entidades fundamentales. De una parte, permitirá a potenciales vendedores mayoristas la gestión centralizada de todos los productos que tienen en venta.
De otra, se ofrecerán estadísticas centralizadas, disponibles para los propios vendedores o terceros, previo acuerdo. 

### Arquitectura
La arquitectura del proyecto se encuentra detallada en el siguiente [enlace](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/milestones/README_1.md)

#### Microservicios

Ambos microservicios siguen la siguiente estructura de paquetes:

+ controller: contiene los endpoint de la aplicación
+ service: lógica de negocio
+ repository: implementación de acceso a datos 
+ model: entidades del negocio

Esta estructura está asociada con el patrón de diseño [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller). Si bien por si sola no garantiza el éxito ni el uso de patrones y principios de diseño fundamentales (como los definidos en [Design Patterns: Elements of Reusable Object-Oriented Software](https://en.wikipedia.org/wiki/Design_Patterns)), proporciona una estructura básica de trabajo configurable y adaptable a nuestras necesidades. Algunos ejemplos de frameworks que utilizan esta etructura o versiones de la misma son [Yii](https://www.yiiframework.com/), [Play](https://www.playframework.com/) y [ADF](https://www.oracle.com/database/technologies/developer-tools/adf/). Este último resulta particularmente atractivo, puesto que propone un enfoque bottom-top para desarollar la aplicación, poniendo enfásis primordialmente en el diseño del modelo de datos.

En el siguiente enlace se exponene algunos detalles de interés de la implementación

#### Contenedores

Un contenedor es una unidad estándar de software que empaqueta el código y todas sus dependencias para que la aplicación se ejecute de forma rápida y confiable de un entorno a otro.
En el proyecto se emplea Docker, aunque existen otros motores de contenedores como [podman] (https://podman.io/). Para más detalles de los contenedores utilizados y su configuración
seguir el siguiente [enlace]


##### Herramienta de construcción

buildtool: pom.xml

Al ser un proyecto Java, en cuanto a herramientas de construcción se refiere dos de las más utilizadas son
[Maven](https://maven.apache.org/) y [Gradle](https://gradle.org/). El proyecto utiliza Maven, dada la amplia documentación existente y la posibilidad de manejar las dependencias de manera centralizada a través del fichero pom.xml

Mediante un conjunto de instrucciones tales como *clean*, *validate*, *test* y package, maven descarga las dependencias, corre los tests y empaqueta toda la aplicación en un archivo .jar

Para más detalles de como utilizamos Maven consultar el siguente [enlace](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/maven_detail.md)

##### Versiones de lenguaje y herramientas

El proyecto, o la mayor parte del mismo, se va a desarrollar en Java. Si bien se podría prescindir de un framework, hay varios, o al menos algunos que nos facilitan ciertas tareas relacionadas al desarrollo que de otra forma hay que configurar desde cero, con su consecuente costo en tiempo. En este proyecto se hace uso extensivo de [Spring Boot](https://github.com/spring-projects/spring-boot). La versión estable más reciente de Spring Boot es la 2.2.2. Soporta las versiones de Java 8, 11 y 13. A pesar de ser Java 8 la más antigua se seleccionó por su madurez y su alta compatibilidad con otras herramientas del ecosistema.

Para más detalles de Spring Boot y su uso en el proyecto, consultar el siguiente [enlace](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/spring_boot_detail.md)

##### Pruebas

Al inicializar un proyecto [Spring Boot](https://start.spring.io/) se pueden seleccionar un conjunto de dependencias en función de las necesidades del proyecto. Al seleccionar Spring Web se añaden las dependencias necesarias para desarrollo Restful asi como la configuración automática de un servidor [Tomcat](http://tomcat.apache.org/) embebido. Adicionalmente, en el pom.xml se lista la dependencia *spring-boot-starter-test*. Esta dependencia incluye lo siguiente:

+ [JUnit](https://junit.org/junit5/): *framework* de pruebas para Java en su versión 5
+ [Mockito](https://site.mockito.org/): *mocking framework* de pruebas unitarias, en este caso la versión 3
+ [AssertJ](https://assertj.github.io/doc/): librería de aserciones, versión 3
+ [Hamcrest](http://hamcrest.org/JavaHamcrest/): *framework* de emparejadores (*matchers*) flexible, versión 2 

Las pruebas del proyecto se implementan utilizando este conjunto de componentes, aprovechando su compatibilidad con Spring Boot.

Para el reporte de cobertura de código utilizamos [Jacoco](https://www.eclemma.org/jacoco/), que en su web oficial se define de la siguiente manera: "... registra qué partes de su código Java se ejecutan durante el inicio de un programa, generalmente se usa con pruebas automatizadas como las pruebas unitarias de JUnit". Para información adicional respecto a la configuración de JaCoCo visitar el siguiente [enlace](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/test_detail.md) 

##### Integración continua

Detalles de travis y su configuración, [aquí](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/travis_detail.md)

### Licencia
[GNU General Public License v3.0](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/LICENSE) 


### Bibliografía general (tanto para consulta como para copia de instrucciones, de las cuales no pretendo por ningún motivo atribuirme su creación)

[Desarrollo basado en pruebas](https://jj.github.io/CC/documentos/temas/Desarrollo_basado_en_pruebas)

[Clases de Cloud Computing](https://github.com/JJ/CC-19-20)

[Spring Boot](https://spring.io/projects/spring-boot)

[Docker] (https://www.docker.com/resources/what-container)



