# Travis

Es un sistema de integración continua que entre sus principales características podemos enunciar que no necesita servidor dedicado, tiene configuración inicial simple y permite ejecutar tests en Linux y Mac al mismo
tiempo.

Para utilizarlo es necesario agregar un fichero un .travis.yml en la raíz del proyecto. Este archivo esta compuesto a su vez por una serie de términos como:

phase - Los pasos secuenciales de un *job*. Por ejemplo, la fase de *install*, viene antes de la fase *script*

job - un proceso automatizado que clona su repositorio en un entorno virtual y luego lleva a cabo una serie de fases, como compilar el código, ejecutar pruebas, etc. Un *job* falla si el código de retorno de la fase *script* no es cero.

build - un grupo de *jobs*. Por ejemplo, una compilación puede tener dos *jobs*, cada uno de los cuales prueba un proyecto con una versión diferente de un lenguaje de programación. Un *build* termina cuando todos sus trabajos están terminados.

En este momento, el .travis.yml del proyecto contiene la siguiente información:

    # Lenguaje de programación utilizado
    language: java
    jdk:
    # Versiones de JDK 8, en este caso utilizamos dos, la propietaria de Oracle y una libre
    - oraclejdk8
    - openjdk8
    # Distribución de linux seleccionada del conjunto que proporciona travis, en este caso Ubuntu Trusty 14.04, la distribución más reciente que todavía soporta la JDK de Oracle
    dist: trusty
    before_install:
    # Proporcionar permisos de lectura y escritura dentro de la carpeta mvnw del microservicio vendors_products 
    - chmod +x vendors_products/mvnw
    script:
    # Ejecutar los test del microservicio vendors_products
    - mvn  -B -f vendors_products/pom.xml test

Por defecto, para descargar las dependencias travis ejecuta lo siguiente:

    mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V

En este caso se omiten la ejecución de los test y la utilización de [javadoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html) (un generador de documentación) El argumento -B indica ejecutar Maven en modo batch, sin esperar ninguna entrada por parte del usuario. -V indica mostrar la versión de Maven que se está utilizando.


### Bibliografía específica (tanto para consulta como para copia de instrucciones, de las cuales no pretendo por ningún motivo atribuirme su creación)

[Travis sitio oficial](https://docs.travis-ci.com/)

[Maven sitio oficial](https://maven.apache.org/)

[Maven opciones de línea de comandos](https://books.sonatype.com/mvnref-book/reference/running-sect-options.html)



