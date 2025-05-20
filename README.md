# Ecommerce-Desafio
Desafio para Factor IT - Applicacion desarrollada con Angular 18 para el frontend y Java 17 + spring boot para backend, utilizando postgreSQL como base de datos


##Clonar el repo
git clone https://github.com/ManciniMaite/Ecommerce-Desafio.git

##Configurar base de datos
* crear una base de datos y modificar el archivo application.properties
dentro de: desafio\src\main\resources hay un archivo application.properties

Modificar las siguientes lineas:
spring.datasource.url=jdbc:postgresql://localhost:{{puertoPostgres}}/{{nombre de la bd}}?charSet=UTF8
spring.datasource.username={{usuario postgres}}
spring.datasource.password={{constrasenia postgres}}

###Levantar backend
dentro de un terminal parados en la carpeta desafio ejecutar:
mvn clean install
mvn spring-boot:run

##Levantar front
Dentro de un terminal parados en la carpeta desafioFront
ejecutar npm install
ng serve -o 

