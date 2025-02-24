package ru.otus.weatherservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.weatherservice.config.ApiMethodConfig;
import ru.otus.weatherservice.config.ApiToken;
import ru.otus.weatherservice.model.RealTimeResponse;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WeatherService {

    WebClient webClient;
    ApiMethodConfig apiMethodConfig;
    ApiToken apiToken;

    public Mono<RealTimeResponse> getCurrentWeather(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiMethodConfig.getCurrentWeather())
                        .queryParam("key", apiToken.getApiToken())
                        .queryParam("q", city)
                        .build())
                .retrieve()
                .bodyToMono(RealTimeResponse.class);
    }

    public Mono<RealTimeResponse> getCurrentWeather(Double lat, Double lon) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiMethodConfig.getCurrentWeather())
                        .queryParam("key", apiToken.getApiToken())
                        .queryParam("q", lat + "," + lon)
                        .build())
                .retrieve()
                .bodyToMono(RealTimeResponse.class);
    }

    public Mono<RealTimeResponse> getCurrentWeatherByIp(String ip) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(apiMethodConfig.getCurrentWeather())
                        .queryParam("key", apiToken.getApiToken())
                        .queryParam("q", ip)
                        .build())
                .retrieve()
                .bodyToMono(RealTimeResponse.class);
    }

}
