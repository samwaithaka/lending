# LMS Backend Deployment

## Creating Postgres Database
`create database lmsdb;`

`create user lmsuser with password '******';`

`grant all privileges on database lmsdb to lmsuser;`

## Deployment with on Docker

You need the following installed on the server:
- Docker and Docker Compose
- Git
- Java 17
- Maven

git clone https://github.com/samwaithaka/lending

git clone https://github.com/samwaithaka/trandata

`cd trandata`

`mvn clean package && docker build -t trandata-backend:v1 .`

`cd ../lending`

`mvn clean package && docker build -t lending-backend:v1 .`

`docker compose up --build -d && docker logs -f`

## Viewing and Testing API Endpoints

Swagger UI has been used to view and test API endpoints. Go to the URL below after deployment:

http://{host}:8080/swagger-ui/index.html
