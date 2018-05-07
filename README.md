Statflo
=======
Vague Code Challenge
-----------

### Introduction

The challenge was pretty simple and vague, leaving architecture and design decisions up to the challenger. 

### Goal

Design and implement a REST API to create and query Users.

### Approach

The problem could be resolved with a basic CRUD service, however, in that way, we wouldn't be able to demonstrate architectural principles which are key to modern Internet applications. In this solution, I tried to demonstrate how a microservice architecture can be built using event sourcing and CQRS.
 
First of all, data reading and writing were segregated in different services with different data structures. All input is logged into a message broker and broadcasted to any interested components, such as the reading models.
The reading models listen to the message broker and whenever a new event is received indicating a new User was created, the model is updated with the new User data.

### Technology

The services were developed using Spring Boot. Kafka was used as a message broker with spring-kakfa, and MongoDB was the database used through spring-data.
In order to avoid complexity, configurations were kept at default or minimum values and no optimizations were performed.

A docker compose file (docker-compose-vague-challenge.yml) was provided to start the environment, again, using rudimentary configuration to avoid unnecessary complexity.

### Instructions

It is necessary to create the services Docker images using Maven. To do that, just run "mvn install dockerfile:build" on each service (vague-user-reading-service and vague-user-writing-service); This command will register an Docker image locally. After that, the application can be started using:

docker-compose -f docker-compose-vague-challenge.yml up -d

This Docker compose file will start all the necessary containers.

The services will be available at:

Create User: 

http://localhost:8080/v1/users

Example:

curl -d '{"name":"Lucas","role":"foo"}' -H "Content-Type: application/json" -X POST http://localhost:8080/v1/users

Query User:

http://localhost:8081/v1/users?

Example:

curl http://localhost:8081/v1/users?role=foo

### Final Considerations

As the infrastructure components (Kafka, MongoDB) are running on Docker containers, they aren't available at build time, so the spring-boot tests were removed for the sake of simplicity.
Since there aren't any business rules to be validated, there are no automated unit tests too.
A future implementation would be to use a gateway API just to centralize every operation on a single endpoint and redirect the requests to the proper underlying services.

