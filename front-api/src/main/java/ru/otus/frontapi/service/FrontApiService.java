package ru.otus.frontapi.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.frontapi.dto.FrontApiRequestParam;
import ru.otus.model.weather.RealTimeResponse;

import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FrontApiService {

    WebClient webClient;

    public Mono<RealTimeResponse> getWeather(FrontApiRequestParam requestParam) {
        if(requestParam.getCity() != null) {
           return webClient.get()
                   .uri(uriBuilder -> uriBuilder.path("/real/city")
                           .queryParam("city", requestParam.getCity())
                           .build())
                   .retrieve()
                   .bodyToMono(RealTimeResponse.class);
        }
        else{
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/real/coordinates")
                            .queryParam("lat", requestParam.getLat())
                            .queryParam("lon", requestParam.getLon())
                            .build())
                    .retrieve()
                    .bodyToMono(RealTimeResponse.class);
        }
    }

    public Mono<Double> getDayLength(FrontApiRequestParam requestParam, Date date) {
        return null;
    }
}
