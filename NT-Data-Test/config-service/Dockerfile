FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} config-service.jar
COPY ./config-data-srv/. /configuraciones/
EXPOSE 8081
ENTRYPOINT ["java","-jar","/config-service.jar"]