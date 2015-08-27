PROJECT = sistearth-backend

all: docker

jar:
	mvn clean install

docker: jar
	docker-compose build
	docker-compose up