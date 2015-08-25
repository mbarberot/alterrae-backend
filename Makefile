PROJECT = sistearth-backend

all: jar build

jar:
	mvn clean install

run-java: jar
	java -jar target/$(PROJECT).jar

docker:
	docker build -t $(PROJECT) .

run-docker: docker
	docker start -it -p 80:8080 --rm --name $(PROJECT) $(PROJECT)
