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
The reading models listen to the message broker and whenever a new event is receveived indicating a new User was created, the model is updated with the new User data.

### Technology

The services were developed using Spring Boot.
Kafka as message broker.
Mongodb as the reading model database.



To generate docker images for each service:

mvn install dockerfile:build
