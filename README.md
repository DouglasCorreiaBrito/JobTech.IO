# JobTech.IO

This project is an initiative of a small group of developers to create an inverted vacancy system where the candidate can register his concept of a good vacancy (with salary, benefits, among others) and the company that is willing to hire him under those conditions can contact him

## Starting

These instructions will provide a copy of the project running on your local machine for development and testing purposes.

### Requirements

What you need to install the software and how to install it

`` ``
Apache Maven: http://maven.apache.org/
`` ``
`` ``
JDK 8 : https://www.oracle.com/java/technologies/javase-jdk8-downloads.html 
`` ``
`` ``
MySQL Community Server: https://dev.mysql.com/downloads/mysql/
`` ``

### Installing

The project was created with Spring initializr (https://start.spring.io/) and to "install this project" it is enough that you acquire the source code (via git clone preferably) and import the Jobtech folder in your IDE as " Maven Project "

- You will need to pay attention to the application.properties file (found in path src / main / resources) to configure the access information for your database, example:

Connector and location of your database:
`` ``
spring.datasource.url=jdbc:mysql://localhost:3306/dbjobtech?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false&createDatabaseIfNotExist=true
`` ``

Database access user and password:
`` ``
spring.datasource.username=my_user
spring.datasource.password=my_secret_password
`` ``

- Since you have already met the requirements and have a copy of the code with you, open the terminal in the project folder and type:
`` ``
mvn clean install spring-boot:run
`` ``
-If the project builds correctly, go to the browser and type http://localhost:8080/swagger-ui.html, you should see a page similar to the one below:
!!!INSERT SWAGGER IMG HERE!!!

To access the front end, in the browser, type: http://localhost:8080/page/index.html
!!!INSERT INDEX IMG HERE!!!


## Built With

* [Maven] (https://maven.apache.org/) - Dependency management
* [Spring Boot] (https://spring.io/projects/spring-boot) - Reduced Spring settings
* [Spring Data] (https://spring.io/projects/spring-data) - Data access
* [Spring Security] (https://spring.io/projects/spring-security) - Authentication and access control
* [Spring MVC] (https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html) - Implementing Servlet containers
* [Spring Data] (https://spring.io/projects/spring-data) - Data access
* [HTML + CSS + Bootstrap] (https://getbootstrap.com/) - To give that beautiful face for application
* [Vanilla JS] (http://vanilla-js.com/) - The best JS framework


## Version Control

We use Git and GitHub to version. For available versions, see the [tags in this repository] (https://github.com/DouglasCorreiaBrito/JobTech.IO/tags).

## Authors

* ** Douglas Correia ** - * Trabalho inicial * -(https://github.com/DouglasCorreiaBrito)
* ** Gustavo José    ** - * Trabalho inicial * -(https://github.com/gustuxd)
* ** Daniela Ramo    ** - * Trabalho inicial * -(https://github.com/Daniramo)

As soon as possible, we will build the list of collaborators

## License

This project is licensed under the MIT license - see the [LICENSE.md] file (LICENSE.md) for details

## Thanks

* Tip for anyone whose code was used
* Inspiration
* etc
