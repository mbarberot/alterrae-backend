PROJECT = sistearth-backend

all: docker

jar:
	mvn clean install

docker: jar
	docker build -t sistearth/backend .
	docker-compose up