package ru.otus.weatherservice.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "api.method")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiMethodConfig {
    String forecast;
    String currentWeather;
    String astronomy;
}
