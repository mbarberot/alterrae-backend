Sistearth Backend
=================

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

### Build and run

``make``
Then go to https://localhost
