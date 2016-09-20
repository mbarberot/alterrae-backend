FROM java:8

RUN mkdir /alterrae
WORKDIR /alterrae

ADD app/alterrae-backend.jar /alterrae/alterrae-backend.jar
ADD ssl /alterrae/ssl

EXPOSE 8080 8787

CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787", "-jar", "/alterrae/alterrae-backend.jar"]