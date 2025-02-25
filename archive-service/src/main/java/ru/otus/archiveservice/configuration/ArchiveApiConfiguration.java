package ru.otus.archiveservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for archive service
 */
@Configuration
public class ArchiveApiConfiguration {

    /**
     * The method produces a web client bean
     * @param url base url to {@code weather-service}
     * @return {@link WebClient}
     */
    @Bean
    public WebClient webClient(@Value("${service.weather.baseurl}") String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
