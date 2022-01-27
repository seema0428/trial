FROM openjdk:latest
ADD target/todoitem-api-service.jar todoitem-api-service.jar
EXPOSE 8085
ENTRYPOINT["java","-jar","todoitem-api-service.jar"]