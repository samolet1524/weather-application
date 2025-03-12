package ru.otus.frontapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Configuration
@EnableScheduling
@Slf4j
public class ApplicationConfiguration {

    @Bean
    public WebClient webClient(@Value("${service.url}") String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                })
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            if (log.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder("Request: \n");
                sb.append(clientRequest.url()).append("\n");
                sb.append(clientRequest.method()).append("\n");
                clientRequest
                        .headers()
                        .forEach((_, values) -> values.forEach(sb::append));
                log.debug(sb.toString());
            }
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (log.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder("Request: \n");
                sb.append(clientResponse.toString()).append("\n");
                log.debug(sb.toString());
            }
            return Mono.just(clientResponse);
        });
    }
}
