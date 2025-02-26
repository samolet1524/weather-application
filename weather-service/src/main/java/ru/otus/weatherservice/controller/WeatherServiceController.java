package ru.otus.weatherservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.otus.model.astronomy.AstronomyResponse;
import ru.otus.model.weather.RealTimeResponse;
import ru.otus.weatherservice.service.WeatherService;

@RestController
@RequestMapping("weather/service")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WeatherServiceController {

    WeatherService weatherService;

    @GetMapping("real/city")
    public Mono<RealTimeResponse> getCurrentWeatherByCity(@RequestParam(value = "city") String city) {
        return weatherService.getCurrentWeatherByCityOrIp(city);
    }

    @GetMapping("real/coordinates")
    public Mono<RealTimeResponse> getCurrentWeatherByCoordinates(@RequestParam(value = "lat") Double lat, @RequestParam(value = "lon") Double lon) {
        return weatherService.getCurrentWeather(lat, lon);
    }

    @GetMapping("astronomy/coordinates")
    public Mono<AstronomyResponse> getAstronomyByCoordinates(@RequestParam(value = "lat") Double lat, @RequestParam(value = "lon") Double lon) {
        return weatherService.getAstronomy(lat, lon);
    }

    @GetMapping("astronomy/city")
    public Mono<AstronomyResponse> getAstronomyByCity(@RequestParam(value = "city") String city) {
        return weatherService.getAstronomyByCityOrIp(city);
    }

    @GetMapping("real/{ip}")
    public Mono<RealTimeResponse> getCurrentWeatherByIp(@PathVariable String ip) {
        return weatherService.getCurrentWeatherByCityOrIp(ip);
    }

    @GetMapping("astronomy/{ip}")
    public Mono<AstronomyResponse> getAstronomyByIp(@PathVariable String ip) {
        return weatherService.getAstronomyByCityOrIp(ip);
    }
}
