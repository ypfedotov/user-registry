# User Registry

A simple demo user registry which allows to add, view and search basic user info.

## Running in development environment

### Prerequisites

- Docker 19
- JDK 11
- Maven 3.6
- Node.js 12
- Yarn 1.22

### Start Postgres and Elasticsearch

`./reset-databases.sh`

### Run backend

`cd ./backend`

`mvn clean package spring-boot:run`

### Run frontend

`cd ./front`

`yarn start`
