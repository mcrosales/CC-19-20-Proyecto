# CC-19-20-Proyecto

Repositorio para proyecto de asignatura Cloud Computing de Maestria en Informatica de la Universidad de Granada

## Mayorista

La aplicación Mayorista permitirá a los usuarios la gestión centralizada de todos sus productos. Se incluirán funcionalidades habituales como creación, modificación y gestión de productos, asi como categorización e inventario de los mismos.

###Arquitectura
Constara de dos servicios, uno para los usuarios y otro para los productos como tal.

Utilizaremos el lenguaje Java, particularmente Spring para la implementacion de los servicios y algunos de sus componentes como Spring Cloud. Eureka y Zuul de Netflix para el descubrimiento y ruteo de los servicios respectivamente.

Para la persisntecia se utilizará PostgreSQL.

###Licencia

Se utiliza [GNU General Public License v3.0](https://github.com/mcrosales/CC-19-20-Proyecto/blob/master/LICENSE)