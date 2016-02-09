FROM java:8

RUN mkdir /sistearth
WORKDIR /sistearth

ADD app/sistearth-backend.jar /sistearth/sistearth-backend.jar
ADD ssl /sistearth/ssl

EXPOSE 8080 8787

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787", "-jar", "/sistearth/sistearth-backend.jar"]