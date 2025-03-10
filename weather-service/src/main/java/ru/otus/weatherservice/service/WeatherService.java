package ru.otus.weatherservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.model.astronomy.AstronomyResponse;
import ru.otus.model.weather.RealTimeResponse;
import ru.otus.weatherservice.config.ApiMethodConfig;
import ru.otus.weatherservice.config.ApiToken;
import ru.otus.weatherservice.model.AstronomyAPIResponse;
import ru.otus.weatherservice.util.AstronomyResponseMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"addresses"})
public class WeatherService {

    WebClient webClient;
    ApiMethodConfig apiMethodConfig;
    ApiToken apiToken;
    AstronomyResponseMapper astronomyResponseMapper;
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Cacheable(value = "addresses", key = "#city")
    public Mono<RealTimeResponse> getCurrentWeatherByCityOrIp(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(apiMethodConfig.getCurrentWeather())
                        .queryParam("key", apiToken.getApiToken())
                        .queryParam("q", city)
                        .build())
                .retrieve()
                .bodyToMono(RealTimeResponse.class);
    }

    public Mono<RealTimeResponse> getCurrentWeather(Double lat, Double lon) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(apiMethodConfig.getCurrentWeather())
                        .queryParam("key", apiToken.getApiToken())
                        .queryParam("q", lat + "," + lon)
                        .build())
                .retrieve()
                .bodyToMono(RealTimeResponse.class);
    }

    public Mono<AstronomyResponse> getAstronomyByCityOrIp(String city, Date date) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(apiMethodConfig.getAstronomy())
                        .queryParam("key", apiToken.getApiToken())
                        .queryParam("q", city)
                        .queryParam("dt", DATE_FORMAT.format(date))
                        .build())
                .retrieve()
                .bodyToMono(AstronomyAPIResponse.class)
                .map(astronomyResponseMapper::toAstronomyResponse).map(astronomyResponse -> {
                    astronomyResponse.setLastUpdated(date);
                    return astronomyResponse;
                });
    }

    public Mono<AstronomyResponse> getAstronomy(Double lat, Double lon) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(apiMethodConfig.getAstronomy())
                        .queryParam("key", apiToken.getApiToken())
                        .queryParam("q", lat + "," + lon)
                        .build())
                .retrieve()
                .bodyToMono(AstronomyResponse.class);
    }
}
