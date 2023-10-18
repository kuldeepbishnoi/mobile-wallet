# Payment Wallet/ Mobile Wallet - Microservices Application

[![Kubernetes](https://img.shields.io/badge/Kubernetes-Deployed-yellow)](https://kubernetes.io/)
[![Google Kubernetes Engine](https://img.shields.io/badge/GKE-Deployed-yellow)](https://cloud.google.com/kubernetes-engine)
[![Amazon Web Services](https://img.shields.io/badge/AWS-Deployed-yellow)](https://aws.amazon.com/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue)](https://www.docker.com/)
[![Docker Hub](https://img.shields.io/badge/Docker%20Hub-Ready-blue)](https://hub.docker.com/)
[![JWT Authentication](https://img.shields.io/badge/JWT%20Authentication-Feature-brightgreen)](https://jwt.io/)
[![API Gateway](https://img.shields.io/badge/API%20Gateway-Feature-blue)](https://your-api-gateway-url-here)
[![Redis](https://img.shields.io/badge/Redis-Feature-red)](https://redis.io/)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-Feature-red)](https://kafka.apache.org/)


This repository contains a Payment Wallet microservices application with three microservices:

1. **Bank Account Microservice**
   - Docker Image: [kuldeepbishnoi/mobilewallet-bankaccount](https://hub.docker.com/r/kuldeepbishnoi/mobilewallet-bankaccount)
   - API Endpoint: [http://34.122.112.133:8002/api/v1/](http://34.122.112.133:8002/api/v1/)    

2. **Transaction Microservice**
   - Docker Image: [kuldeepbishnoi/mobilewallet-transaction](https://hub.docker.com/r/kuldeepbishnoi/mobilewallet-transaction)
   - API Endpoint: [http://34.170.15.243:8001/api/v1/](http://34.170.15.243:8001/api/v1/)

3. **User Microservice**
   - Docker Image: [kuldeepbishnoi/mobilewallet-user](https://hub.docker.com/r/kuldeepbishnoi/mobilewallet-user)
   - API Endpoint: [http://34.16.9.34:8000/api/v1/](http://34.16.9.34:8000/api/v1/)

## Application Architecture

![Entity-Relationship Diagram](images/architecture.png)

The application's database is deployed on Amazon Web Services (AWS). **This is still @ local machine and will be deployed soon other architecture that don't use API Gateway, Kafka, Redis is deployed and its link is available above**.

## Database Relation

![Entity-Relationship Diagram](images/eer_diagram.png)

The application's database is deployed on Amazon Web Services (AWS) with the provided entity-relationship diagram. This diagram showcases the database's structure.

## JWT Token Authentication

For user authentication, the application uses JWT token authentication. Below are the images illustrating the login and registration processes:

![Login](images/login.png)
![Registration](images/register.png)

## Microservices and Docker Files

The repository is organized into three microservices folders:

- [bankAccount/](microservices/bankAccount) - Bank Account Microservice
- [transaction/](microservices/transaction) - Transaction Microservice
- [user/](microservices/user) - User Microservice

You can find the jar file(embedded server) and corresponding source code for each microservice in their respective folders.

## Kubernetes Deployment

For scalability and management I have used  kubernetes.
