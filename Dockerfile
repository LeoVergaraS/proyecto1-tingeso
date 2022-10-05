FROM openjdk:17
ARG JAR_FILE=target/proyecto1-tingeso.jar
EXPOSE 8090
COPY ${JAR_FILE} proyecto1-tingeso.jar
ENTRYPOINT ["java","-jar","/proyecto1-tingeso.jar"]