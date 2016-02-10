Sistearth Backend
=================

[![Build Status](https://travis-ci.org/mbarberot/sistearth-backend.svg?branch=master)](https://travis-ci.org/mbarberot/sistearth-backend)

A RESTful API for Sistearth v4 implemented in Java

Reason (vs PHP / Symfony backend)
---------------------------------

* KISS
* No more "magic" framework (thinking to you JEE and Symfony)
* Dev comfort : explicit types, easier to monitor/debug, maven 
 
Tools
-----

### Dev

* Java
* Spark framework (light, easy and simple)
* Guava
* Sql2o
* Jackson
* Lombok
* JUnit

### Deployment

* Docker
* docker-compose
* MariaDB (+ official docker image)

How to use
----------

### What you need

* Java 8
* Maven
* Docker & docker-compose
* Source code

### Create keystore

``keytool -keystore keystore -alias sistearth -genkey -keyalg RSA``  
Set keystore password : ``sistearth``  
Hit return to question ``Enter key password for <sistearth>``

### Build

``mvn clean install``

This will build, test, package code and build docker image.

### Run

``docker-compose up`` or ``docker-compose start``

This will run both server and databse container + adminer to manage db

Useful tools
------------

### Sonar

Source code analysis tool.

  1. Run Sonarqube server with docker
    * The first time : ``docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube:5.1``
    * Nex time : ``docker start sonarqube``
  2. Launch analysis with maven
    * ``mvn sonar:sonar``
  
### Adminer

PHPMyAdmin-like, but lighter

  1. Run with docker : ``docker run -d -p 8888:80 --link sistearthbackend_database_1:database clue/adminer``  
  2. Go to http://localhost:8888
  3. Login using db credential and host will be 'database'.
