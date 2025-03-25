ARG VERSION=0.0.1-SNAPSHOT
FROM openjdk:17
MAINTAINER "samwaithaka@gmail.com"

WORKDIR /app

COPY target/lending-0.0.1-SNAPSHOT.jar ./

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "./lending-0.0.1-SNAPSHOT.jar"]