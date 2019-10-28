# CC-19-20-Proyecto

Repositorio para proyecto de asignatura Cloud Computing de Maestria en Informática de la Universidad de Granada

## Mayorista

La aplicación Mayorista permitirá a los vendedores la gestión centralizada de todos sus productos. Se incluirán funcionalidades habituales como creación, modificación y gestión de productos, asi como categorización e inventario de los mismos.

### Arquitectura
Se utilizará una arquitectura basada en microservicios. Tendremos dos microservicios, uno para gestionar los vendedores y otro para gestionar sus productos. 

El primer servicio lidiará con la creación y modifiación de vendedores, además de proveer indicadores importantes como vendedores más exitosos.

El segundo servicio permitirá creación de productos, incluyendo información relativa a precio, cantidad en existencia, marca y fecha de caducidad.

Para la comunicación con y entre los servicios utilizaremos REST.

Utilizaremos el lenguaje Java, particularmente Spring para la implementacion de los servicios y algunos de sus componentes como Spring Boot, Spring Cloud y Spring Data Rest.
Se emplearán Eureka y Zuul de Netflix para el descubrimiento y ruteo de los servicios respectivamente. Para la configuracion remota se utilizará [Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/reference/html/).

En cuanto al logging centralizado se utilizará [Loggly](https://www.loggly.com/) o [Logstash](https://www.elastic.co/es/products/logstash)

Para la persisntecia se utilizará como servidor de base de datos relacional PostgreSQL. 

### Licencia

Se utiliza [GNU General Public License v3.0](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/LICENSE)