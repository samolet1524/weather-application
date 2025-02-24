package ru.otus.archiveservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableScheduling
public class ArchiveApiConfiguration {

        @Bean
    public WebClient webClient(@Value("${service.weather.baseurl}") String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
