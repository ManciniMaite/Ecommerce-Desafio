# Ecommerce-Desafio
Desafio para Factor IT - Applicacion desarrollada con Angular 18 para el frontend y Java 17 + spring boot para backend, utilizando postgreSQL como base de datos

## Requisitos
Para poder ejecutar este proyecto de forma local:

* Node.js >= 18: Descargable desde nodejs.org.

* npm >= 8: se instala junto con Node.js.

* Angular CLI compatible con Angular 18: Se puede instalar globalmente con npm install -g @angular/cli.

* Java 17 (JDK 17): configurar la variable de entorno JAVA_HOME.

* Maven >= 3.6

* PostgreSQL

## Clonar el repo
git clone https://github.com/ManciniMaite/Ecommerce-Desafio.git
en un terminal ejecutar:
* cd Ecommerce-Desafio
* git checkout master 

para moverse a la rama master

## Configurar base de datos
* crear una base de datos y modificar el archivo application.properties.
  
dentro de: desafio\src\main\resources hay un archivo application.properties, modificar las siguientes lineas:
* spring.datasource.url=jdbc:postgresql://localhost:{{puertoPostgres}}/{{nombre de la bd}}?charSet=UTF8
* spring.datasource.username={{usuario postgres}}
* spring.datasource.password={{constrasenia postgres}}

### Levantar backend
dentro de un terminal, parados en la carpeta desafio ejecutar:
mvn clean install
mvn spring-boot:run

El servicio arrancará en: http://localhost:8080 

La interfaz de Swagger UI está habilitada para pruebas de endpoints en: http://localhost:8080/swagger-ui/index.html#/

Dentro de la carpeta resources se encuentra un archivo data.sql con un script que se ejecuta al levantar la aplicacion para cargar los datos de inicio

Este script cuenta con:
* 15 productos 
* 4 usuarios
* 2 fechas especiales
* compras de los usuario

### Levantar front
Dentro de un terminal parados en la carpeta desafioFront ejecutar: 
* npm install
* ng serve -o

Se levantara en http://localhost:4200



