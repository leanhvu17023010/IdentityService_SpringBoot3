# Identity service
This microservice is responsible for:
* Onboarding users
* Roles and permissions
* Authentication

## Tech stack
* Build tool: maven >= 3.9.5
* Java: 17
* Framework: Spring boot 3.5.5
* DBMS: MySQL

## Prerequisites
* Java SDK 17
* A MySQL server

## Start application
`mvn spring-boot:run`

## Build application
`mvn clean package`


## Docker guideline
### Create network:
`docker network create devteria-network`
### Start MySQL in devteria-network
`docker run --network devteria-network --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.43-debian`
### Run your application in devteria-network
`docker run --name identity-service --network devteria-network -p 8080:8080 -e DBMS_CONNECTION=jdbc:mysql://mysql:3306/identity_service identity-service:0.0.1`


