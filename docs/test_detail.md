# Covertura de pruebas
Para la cobertura de pruebas (test coverage) utilizamos JaCoCo añadiendo una serie de líneas en el pom.xml para ejecutar el analísis desde nuestra herramienta de construcción, quedando de la manera siguiente:

    <plugin>
				<groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<version>0.8.5</version>
				<configuration>
					<excludes>
						<exclude>ugr/cc/vendors_products/VendorsProductsApplication.class</exclude>
						<exclude>ugr/cc/vendors_products/controller/VendorController.class</exclude>
						<exclude>ugr/cc/vendors_products/model/Vendor.class</exclude>
					</excludes>
				</configuration>
				<executions>
					<execution>
						<goals>
							<goal>prepare-agent</goal>
						</goals>
					</execution>
					<execution>
						<id>jacoco-check</id>
						<phase>test</phase>
						<goals>
							<goal>check</goal>
						</goals>
						<configuration>
							<rules>
								<rule>
									<element>PACKAGE</element>
									<limits>
										<limit>
											<counter>INSTRUCTION</counter>
											<value>COVEREDRATIO</value>
											<minimum>0.95</minimum>
										</limit>
									</limits>
								</rule>
							</rules>
						</configuration>
					</execution>
					<execution>
						<id>report</id>
						<phase>test</phase>
						<goals>
							<goal>report</goal>
						</goals>
					</execution>
				</executions>
			</plugin>


Se añade a la sección de plugins del pom para ejecutar sus tareas de conjunto con maven. En este segmento:

                <configuration>
					<excludes>
						<exclude>ugr/cc/vendors_products/VendorsProductsApplication.class</exclude>
						<exclude>ugr/cc/vendors_products/controller/VendorController.class</exclude>
						<exclude>ugr/cc/vendors_products/model/Vendor.class</exclude>
					</excludes>
				</configuration>

se está indicando que al realizar el análisis, no se tengan en cuenta las clases asociadas a los vendedores, que si bien son importantes, por ahora no tienen ninguna implementación de peso y por lo tanto reducirán la cobertura innecesariamente. La clase principal también se deja fuera porque no tiene ningún test ya que obligatoriamente tiene que funcionar para levantar la aplicación.

Al indicar:

                <execution>
						<id>jacoco-check</id>
						<phase>test</phase>
						<goals>
							<goal>check</goal>
						</goals>
						<configuration>
							<rules>
								<rule>
									<element>PACKAGE</element>
									<limits>
										<limit>
											<counter>INSTRUCTION</counter>
											<value>COVEREDRATIO</value>
											<minimum>0.95</minimum>
										</limit>
									</limits>
								</rule>
							</rules>
						</configuration>
					</execution>

 se especifica que el chequeo de cobertura se ligue a la fase test de maven (cuando ejecutamos maven test) y especificamos como mínimo un 95% de covertura para las instrucciones del proyecto, revisando cada paquete (PACKAGE). Si no se cumple este requisito, el build de maven falla siempre que se corran los test.

En este otro fragmento, se ata la generación del reporte de resultados también a la fase test de maven.

                    <execution>
						<id>report</id>
						<phase>test</phase>
						<goals>
							<goal>report</goal>
						</goals>
					</execution>

Esto genera un reporte como este en la carpeta *target/site/jacoco* (el directorio se puede cambiar):

![](img\coverage.jpg)


### Bibliografía específica (tanto para consulta como para copia de instrucciones, de las cuales no pretendo por ningún motivo atribuirme su creación)

[JaCoCo sitio oficial](https://www.eclemma.org/jacoco/)

[Automated code coverage of unit tests with JaCoCo and Maven](https://automationrhapsody.com/automated-code-coverage-of-unit-tests-with-jacoco-and-maven/)

[Intro to JaCoCo](https://www.baeldung.com/jacoco)

[Maven – JaCoCo code coverage example](https://mkyong.com/maven/maven-jacoco-code-coverage-example/)