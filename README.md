# CC-19-20-Proyecto

Repositorio para proyecto de asignatura Cloud Computing de Maestría  en Informática de la Universidad de Granada

## Mayorista

La aplicación Mayorista constará de dos entidades fundamentales. De una parte, permitirá a potenciales vendedores mayoristas la gestión centralizada de todos los productos que tienen en venta.
De otra, se ofrecerán estadísticas centralizadas, disponibles para los propios vendedores o terceros, previo acuerdo. 

### Arquitectura
Se utilizará una arquitectura basada en microservicios. Tendremos dos microservicios. El primero se encargará de gestionar los vendedores y sus productos.
Incluirá funcionalidades como creación, modificación y gestión de productos, así como categorización e inventario de los mismos.
 
El segundo microservicio se encargará de gestionar las estadísticas. Entre los datos ofrecidos podemos
enunciar: total de productos en venta y su precio total, vendedores con más productos e histórico de productos más demandados.

Contaremos con los componentes tradicionales de una arquitectura basada en microservicios tales como ruteo, logging centralizado y configuración distribuida. Estos componentes se muestran en la siguiente imagen y serán descritos a continuación.

[Diagrama de arquitectura](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/docs/img/diagrama_arquitectura.jpg)

#### Lenguaje 

El lenguaje utilizado será Java. Dentro el universo de posibilidades que ofrece este lenguaje, se prioriza el trabajo con el framework Spring, uno de los framework más maduros en el ecosistema Java. 

Para el desarrollo de los mircoservicios utilizaremos [Spring Boot](https://github.com/spring-projects/spring-boot), el cual permite la creación de aplicaciones basadas en Spring de una manera rápida, reduciendo la configuración inicial necesaria.
Este proyecto ocupa el [6 lugar](https://github.com/search?l=Java&q=stars%3A%3E%3D500&type=Repositories) entre todos los proyectos Java de Github. Ambos microservicios implementarán una API REST. La comunicación entre el microservicio de vendedores/productos
y el de estadísticas se llevará a cabo por medio de peticiones http asíncronas, haciendo uso de la anotación @Async, lo cual permitirá realizar las llamadas sin esperar por su completamiento.

#### Configuración distribuida
Este aspecto estará cubierto por [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/reference/html/), el cual es compatible con aplicaciones Spring pero también con aplicaciones en otros lenguajes diferentes a Java.

#### API Gateway
Como gateway se utilizará Spring Cloud Zuul, un componente del framework Spring basado en el proyecto [Zuul de Netflix](https://github.com/Netflix/zuul) el cual incluye ruteo, monitoreo y seguridad entre otros aspectos.

### Logging centralizado
En cuanto al logging centralizado, [Logstash](https://www.elastic.co/es/products/logstash) provee varias funcionalidades, entre ellas exportar los logs a varias fuentes de datos,
desde ficheros csv hasta bases de datos NoSql como MongoDB.


### Almacenes de datos
Para el microservicio de vendedores/productos, se utilizará como servidor de base de datos relacional PostgreSQL, dada la estrecha relación entre vendedores y sus productos.
Con el obejtivo de almacenar las estadísticas, se empleará una base de datos NoSQL, particularmente MongoDB, pudiendo almacenar aquí los logs centralizados de ser necesario.

### Licencia
Se utiliza [GNU General Public License v3.0](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/LICENSE) 