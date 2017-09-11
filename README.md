
# About

Asynchron messaging application by Ali Ekber Celik.This application developed under Ubuntu 16.04 x64 OS with Java 8.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring MVC 4
* JMS & ActiveMQ 5.13
* Tomcat 8
* Maven 3.5
* Eclipse


Before build our project using Maven,we need to start ActiveMQ for our test scripts.

## Run ActiveMQ

$> cd your_activemq_path/bin

$> sudo ./activemq start


Then,following two commands must be run seperately for VehicleManager and VehicleProcessor

## Enter application folder

$> cd your_application_folder_path

## Build application from source

$> mvn clean install


After that,we can deploy VehicleManager to Tomcat in Eclipse.You can add new vehicle and redirect an existing vehicle using this project.Basically,main page of this project is http://localhost:8080/VehicleManager/ You can also navigate with links below the page to list existing vehicles' status and positions.

When you completed vehicle operations,you can deploy VehicleProcessor project to Tomcat in Eclipse.This project will process your operations.And it will change these vehicles' status.New statuses can be seen in VehicleManager project web ui.

Also you can see your queues' status in ActiveMQ admin page(http://localhost:8161/admin/queues.jsp)
