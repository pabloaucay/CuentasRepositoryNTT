FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY service-cuentas/${JAR_FILE} service-cuentas.jar
COPY service-clientes/${JAR_FILE} service-clientes.jar
COPY service-movimientos/${JAR_FILE} service-movimientos.jar
ADD load.sh .
EXPOSE 8092
EXPOSE 8091
EXPOSE 8093

CMD ["bash", "load.sh"]






