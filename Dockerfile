FROM java:8

RUN mkdir /sistearth
WORKDIR /sistearth

ADD target/sistearth-backend.jar /sistearth/sistearth-backend.jar

EXPOSE 8080

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "/sistearth/sistearth-backend.jar"]