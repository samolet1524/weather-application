package ru.otus.weatherservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import ru.otus.weatherservice.config.ApiMethodConfig;
import ru.otus.weatherservice.config.ApiToken;
import ru.otus.weatherservice.model.AstronomyResponse;
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
                .uri(prepareUriComponents(apiMethodConfig.getCurrentWeather())
                        .queryParam("q", city)
                        .build()
                        .toUri())
                .retrieve()
                .bodyToMono(RealTimeResponse.class);
    }

    public Mono<RealTimeResponse> getCurrentWeather(Double lat, Double lon) {
        return webClient.get()
                .uri(prepareUriComponents(apiMethodConfig.getCurrentWeather())
                        .queryParam("q", lat + "," + lon)
                        .build()
                        .toUri())
                .retrieve()
                .bodyToMono(RealTimeResponse.class);
    }

    public Mono<RealTimeResponse> getCurrentWeatherByIp(String ip) {
        return webClient.get()
                .uri(prepareUriComponents(apiMethodConfig.getCurrentWeather())
                        .queryParam("q", ip)
                        .build()
                        .toUri())
                .retrieve()
                .bodyToMono(RealTimeResponse.class);
    }

    public Mono<AstronomyResponse> getAstronomy(String city) {
        return webClient.get()
                .uri(prepareUriComponents(apiMethodConfig.getAstronomy())
                        .queryParam("q", city)
                        .build()
                        .toUri())
                .retrieve()
                .bodyToMono(AstronomyResponse.class);
    }

    public Mono<AstronomyResponse> getAstronomy(Double lat, Double lon) {
        return webClient.get()
                .uri(prepareUriComponents(apiMethodConfig.getAstronomy())
                        .queryParam("q", lat + "," + lon)
                        .build()
                        .toUri())
                .retrieve()
                .bodyToMono(AstronomyResponse.class);
    }

    @Scheduled(fixedDelay = 300000)
    public void runJob() {

    }

    private UriComponentsBuilder prepareUriComponents(String method) {
        return UriComponentsBuilder
                .newInstance()
                .path(method)
                .queryParam("key", apiToken.getApiToken());
    }

}
