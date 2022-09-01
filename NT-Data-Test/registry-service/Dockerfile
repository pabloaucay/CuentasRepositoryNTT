FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} registry.jar
EXPOSE 8099
ENTRYPOINT ["java","-jar","/registry.jar"]