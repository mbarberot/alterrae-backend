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

### Deployment

* Docker

How to use
----------

### What you need

* Java 8
* Maven
* Source code

### Create keystore

``keytool -keystore keystore -alias sistearth -genkey -keyalg RSA``  
Set keystore password : ``sistearth``  
Hit return to question ``Enter key password for <sistearth>``

### Compile

``make jar``

### Run with Java

``make run-jar``  
Then, go to https://localhost:8080

### Run with docker

``make run-docker``  
Then go to https://localhost
