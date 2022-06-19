# Guía de creación del proyecto:
## Creción del arquetipo 
Para crear el arquetipo maven quickstart para el proyecto ejecute:
```sh

mvn archetype:generate -DgroupId=com.company -DartifactId=resolucionEq -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
## Editar pom.xml
Edite resolucionEq\pom.xml para que cree el archivo Manifest:
```sh
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
    <properties>
        <maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.source>1.8</maven.compiler.source>
    </properties>
  <groupId>com.company</groupId>
  <artifactId>resolucionEq</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>resolucionEq</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
<build>
 	<plugins>
    	<plugin>
    		<groupId>org.apache.maven.plugins</groupId>
     		<artifactId>maven-jar-plugin</artifactId>
    		<configuration>
    			<archive>
        			<manifest>
        			    <mainClass>com.company.App</mainClass>
        			</manifest>
    			</archive>
    		</configuration>
    	</plugin>
	</plugins>
</build>
</project>
```
## Clase intervalo
Representa el intervalo (a,b) en el que se busca la raíz de la ecuación. Edite el archivo:  resolucionEq\src\main\java\com\intervalo.java
```sh
package com.company;
class intervalo{
public double a;
public double b;
public intervalo(double a_, double b_){
	a=a_;
	b=b_;
}
}
```
## Clase ecuacion
Modela los métodos de resolución de ecuaciones. El método estático raiz, calcula la raíz de una ecuación F , pasada como argumento, en el intervalo a,b, con una precisión p.
Editar resolucionEq\src\main\java\com\ecuación.java 
```sh
package com.company;
import java.util.stream.Stream;
import java.util.function.DoubleUnaryOperator;
class ecuacion{
public static double raiz(DoubleUnaryOperator F, double a, double b, double p){
	intervalo i=  Stream
		.iterate(new intervalo (a,b), n -> (F.applyAsDouble((n.a+n.b)/2.0)*F.applyAsDouble(n.a)<0)? new intervalo(n.a, (n.a+n.b)/2): new intervalo((n.a+n.b)/2,n.b))
		.filter(n->Math.abs(n.b-n.a)<p) 
                .findFirst()
		.orElse(null);
	if (i!= null)
		return i.a;
	else
		return a-1;
}
}
```
## Programa principal
Edicion de  resolucionEq\src\main\java\com\App.java
```sh
package com.company;
public class App {
    public static void main( String[] args )
    {
	System.out.print(ecuacion.raiz(x->x*x-1,0,1.5,0.0001));
    }
}
```
# Compilar
Para compilar y empaquetar el proyecto:
```sh
cd resolucionEq
mvn package
```
## Ejecutar 
Para ejecutar el .jar generado:
```sh
java -jar target\resolucionEQ-1.0-SNAPSHOT.jar
```


