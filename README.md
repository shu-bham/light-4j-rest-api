
# OpenAPI Light-4J Server

### Running Mongo DB locally

The only dependency for running these examples is [Docker Compose][docker].

[docker]: https://docs.docker.com/compose/install/

Once Docker Compose is installed, you can start Mongo DB using the following command:

```sh
$ docker-compose -f docker/docker-compose-mongo.yml up -d
```

Mongo shell can be accessed via
            
```sh
$ docker-compose exec my-mongo bash
```

Create database ```swiftdb``` using :
```shell script
use swiftdb
```

### Build and Start

The scaffolded project contains a single module. A fat jar server-fat-{version}.jar will be generated in build/libs directory after running the build command below.

```
./gradlew clean build
```

With the fatjar in the build/libs directory, you can start the server with the following command.

```
java -jar build/libs/light-4j-rest-api-fat-{version}.jar
```

### Access the APIs from Swagger-UI

```
http://localhost:8080/specui.html
```

Optionally, endpoint details can be found in ```handler.yml```

### Files to be configured:

1) ```src/main/resources/config/mongo.json``` contains default mongo host uri, database name 

2) ```src/main/resources/config/users.json``` contains dummy User data 





