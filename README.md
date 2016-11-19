# README #

### What is this repository for? ###

* This is a public repository for demonstrating Batch + JavaMail development using JAVA Spring Batch, Security, Hibernate, javaMail + Spring Boot.
* I am more than happy and welcome for any suggestions and improvements. 
* Version 1.0.1

### How do I get set up? ###

* JDK 1.8 + Tomcat 8.
* Excute SAMPLE.sql to make database ready.
* Excute BATCH_MYSQL.sql to make batch database ready.
* Change dal.properties located in ../src/main/resources for setting up DB connections. 
* Change email.properties located in ../src/main/resources for setting up Email connections. 
* Import project as Maven project.
* Update & clean project if necessary.
* Run Unit Test as JavaApplication or build then and put it into tomcat.
* Play around with Junit, Happy coding.

### Structure guidelines ###

* Batch Launcher + Tasklet
  * Refer to package(launcher, publisher, subscriber)
  * Launcher defines the entry of the job.
  * Tasklet implements the back logic funcationality.
  
* Java Mail
  * Refer to package(service)
  * Java mail responses for sending simple/rich text mail.

* Spring Boot
  * Using Spring Boot to simplify dependency management.
  
### NOTE: The structure and component below is mostly same as my other samples. They CAN be a dependency JAR file or files depends on your application needs. This gives flexibility to implement any functionalities upon it or any other new technologies might comming. Yes, finger cross! ###

* Manager + Service 
  * Refer to package(manager, service, abstract)
  * Manager is responsible for (1)DAL, (2)mapping entity to model, & (3)business logic implementation.
  * Manager encapsulates DAL and only output model as media to communicate.
  * Service is responsible for (1)pre-validating data & (2)exposing service.
  
* Hibernate Entity + DAO + Implementation
  * Refer to package(entity, dao, impl)
  * Entity represents the DB table/view structure.
  * DAO represents the interface signature of hibernate implementation.
  * Impl represents the implementation.

* Utility tools
  * Refer to package(utility)
  * Utility represents the customized tools & date convertors for application.
  
* XML model from XSD schema
  * Refer to (model, ../src/main/resource/schemas, XMLBindings.xml)
  * Model represents the output data structure.
  * Model generated from XSD schema using JAXB.

* Constant & configuration & exception
  * Refer to package(constant, config, exception)
  * Constant represents the constants such as error code/message, enums, etc.
  * Configuration represents the configuration.
  * Exception implements customized exceptions.
  
* Unit test
  * Refer to package(test)
  * Test including hibernate & utility
  * Test including batch
  
* Application setting
  * Refer to (../src/main/resources/config/*, ../src/main/resources/webapp/WEB-INF/*)
  
### Who do I talk to? ###

* Repo owner or admin(tony87c0086@hotmail.com)
* Other community or team contact
