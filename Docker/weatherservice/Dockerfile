FROM openjdk:23
LABEL authors="sburikova"
EXPOSE 9090
COPY weather-service.jar weather-service.jar
ENTRYPOINT ["java", "-jar", "/weather-service.jar"]