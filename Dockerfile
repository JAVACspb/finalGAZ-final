# my dockerfile is awesome
FROM openjdk:18-alpine3.15

ARG JAR_FILE=out/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
