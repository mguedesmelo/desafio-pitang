FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install 

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/car-rental-spring-0.0.1-SNAPSHOT.jar car-rental-spring.jar

ENTRYPOINT [ "java", "-jar", "car-rental-spring.jar" ]
