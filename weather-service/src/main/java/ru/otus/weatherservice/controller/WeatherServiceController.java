package ru.otus.weatherservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.otus.weatherservice.model.RealTimeResponse;
import ru.otus.weatherservice.service.WeatherService;

@RestController
@RequestMapping("weather/service")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WeatherServiceController {

    WeatherService weatherService;
    @GetMapping("city")
    public Mono<RealTimeResponse> getCurrentWeatherByCity(@RequestParam(value = "city") String city) {
        return weatherService.getCurrentWeather(city);
    }
    @GetMapping("coordinates")
    public Mono<RealTimeResponse> getCurrentWeatherByCoordinates(@RequestParam(value = "lat") Double lat, @RequestParam(value = "lon") Double lon) {
        return weatherService.getCurrentWeather(lat, lon);
    }
}
