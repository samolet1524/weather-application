FROM openjdk:23
LABEL authors="sburikova"
EXPOSE 8080
COPY target/frontapi-service.jar frontapi-service.jar
ENTRYPOINT ["java", "-jar", "/frontapi-service.jar"]