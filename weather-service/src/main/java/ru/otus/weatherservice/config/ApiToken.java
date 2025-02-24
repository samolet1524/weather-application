package ru.otus.weatherservice.config;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "api")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiToken {

    @Setter
    String key;
    String apiToken;

    @PostConstruct
    public void init() {
        apiToken = new String(new Base64().decode(key.getBytes()));
    }
}
