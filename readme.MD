# Cloud-Parking

- Performing Cloud Deployment of a set of APIs developed in Spring Boot using clean architecture and the main current best practices in the market.

## Heroku app

https://cloud-parking-br.herokuapp.com/swagger-ui/index.html#

## How to build and run

***With docker-compose*:**

```
docker-compose up 
```

***With docker*:**

```
docker network create --driver bridge parking-net

docker run --name parking-db --network parking-net -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres:10-alpine

docker build -t cloud-parking-image ./infrastructure/docker/app/ && docker run --name cloud-parking-container --network parking-net -p 8080:8080 -d cloud-parking-image

docker build -t nginx-image ./infrastructure/docker/nginx/ && docker run --name nginx --network parking-net -p:80:80 -d nginx-image
```

***Without docker*:**

Add your PostgreSQL connection settings in the .\infrastructure\resources\application.yml file

Build and generate the artifact file with the maven command `mvn clean package` and then run the maven spring boot plugin     
`mvn spring-boot:run` (or the java command ` java -jar ./infrastructure/target/infrastructure-0.0.1-SNAPSHOT.jar`).

The API documentation will be accessible at [http://localhost:8080/swagger-ui/index.html](http:localhost:8080)

## How to authenticate

Login with user admin and password admin on the authentication endpoint and send the received JWT token in the request header.

Username and password can be changed in application.yml file.

## Samples

Login request:

```
curl --location --request POST 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "admin",
    "password" : "admin"
}'
```

Login response:

```
{
    "name": "admin",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGFkbWluIiwiaXNzIjoiQ2xvdWRQYXJraW5nIiwiaWF0IjoxNjU5MTQ5MTM5LCJleHAiOjE2NTkyMzU1Mzl9.k8v0yqsqdPBea01vFeHW0UiNoiHkbTQhEmDfMhTTEuTR6Y8zMPeFpS6NrKZvgCKHY-B2YjQeaYm6vmGfa465WA"
}
```

Parking create:

```
curl --location --request POST 'http://localhost:8080/parking' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGFkbWluIiwiaXNzIjoiQ2xvdWRQYXJraW5nIiwiaWF0IjoxNjU5MTQ5MTM5LCJleHAiOjE2NTkyMzU1Mzl9.k8v0yqsqdPBea01vFeHW0UiNoiHkbTQhEmDfMhTTEuTR6Y8zMPeFpS6NrKZvgCKHY-B2YjQeaYm6vmGfa465WA' \
--header 'Content-Type: application/json' \
--data-raw '{
  "license": "AAA-1234",
  "state": "PR",
  "model": "BMW",
  "color": "black"
}'
```

Parking list:

```
curl --location --request GET 'http://localhost:8080/parking?search&page=0&perPage=10&sort=entryDate&dir=asc' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxLGFkbWluIiwiaXNzIjoiQ2xvdWRQYXJraW5nIiwiaWF0IjoxNjU5MTQ5MTM5LCJleHAiOjE2NTkyMzU1Mzl9.k8v0yqsqdPBea01vFeHW0UiNoiHkbTQhEmDfMhTTEuTR6Y8zMPeFpS6NrKZvgCKHY-B2YjQeaYm6vmGfa465WA'
```

