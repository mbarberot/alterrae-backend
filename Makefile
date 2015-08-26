PROJECT = sistearth-backend

all: run-docker

jar:
	mvn clean install

run-java: jar
	java -jar target/$(PROJECT).jar

docker: jar
	docker build -t $(PROJECT) .

run-docker: docker
	docker run -it -p 443:8080 --rm --name $(PROJECT) $(PROJECT)
