package ru.otus.frontapi.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.frontapi.dto.FrontApiRequestParam;
import ru.otus.model.astronomy.AstronomyResponse;
import ru.otus.model.weather.RealTimeResponse;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FrontApiService {

    WebClient webClient;
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public Mono<RealTimeResponse> getWeather(FrontApiRequestParam requestParam) {
        if (requestParam.getCity() != null) {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/real/city")
                            .queryParam("city", requestParam.getCity())
                            .build())
                    .retrieve()
                    .bodyToMono(RealTimeResponse.class);
        } else {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/real/coordinates")
                            .queryParam("lat", requestParam.getLat())
                            .queryParam("lon", requestParam.getLon())
                            .build())
                    .retrieve()
                    .bodyToMono(RealTimeResponse.class);
        }
    }

    public Mono<Duration> getDayLength(FrontApiRequestParam requestParam, Date date) {
        if (requestParam.getCity() != null) {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/astronomy/city")
                            .queryParam("city", requestParam.getCity())
                            .queryParam("date", DATE_FORMAT.format(date))
                            .build())
                    .retrieve()
                    .bodyToMono(AstronomyResponse.class)
                    .map(this::calculateDayLength);
        } else {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/astronomy/coordinates")
                            .queryParam("lat", requestParam.getLat())
                            .queryParam("lon", requestParam.getLon())
                            .queryParam("date", DATE_FORMAT.format(date))
                            .build())
                    .retrieve()
                    .bodyToMono(AstronomyResponse.class)
                    .map(this::calculateDayLength);
        }
    }

    private Duration calculateDayLength(AstronomyResponse astronomyResponse) {
        return Duration.between(
                LocalTime.parse(astronomyResponse.getAstronomy().getSunrise(),
                        DateTimeFormatter.ofPattern("HH:mm:ss")),
                LocalTime.parse(astronomyResponse.getAstronomy().getSunset(),
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
