FROM openjdk:17.0.2-jdk-slim-buster
LABEL maintainer="https://github.com/staimov"
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/camunda-external-task-1.0.0.jar
ENTRYPOINT ["java","-jar","/app/camunda-external-task-1.0.0.jar"]
