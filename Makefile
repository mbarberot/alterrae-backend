PROJECT = sistearth-backend

all: jar build

jar:
	mvn clean install

build:
	docker build -t $(PROJECT) .