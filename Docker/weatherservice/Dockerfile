FROM openjdk:23
LABEL authors="sburikova"
EXPOSE 9890
COPY weather-service.jar weather-service.jar
ENTRYPOINT ["java", "-jar", "/weather-service.jar"]