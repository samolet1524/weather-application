FROM openjdk:23
LABEL authors="sburikova"
EXPOSE 9097
COPY target/archive-service.jar archive-service.jar
ENTRYPOINT ["java", "-jar", "/archive-service.jar"]