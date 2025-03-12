package ru.otus.weatherservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.otus.model.astronomy.AstronomyResponse;
import ru.otus.model.weather.RealTimeResponse;
import ru.otus.weatherservice.service.WeatherService;

import java.util.Date;

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
    public Mono<AstronomyResponse> getAstronomyByCoordinates(@RequestParam(value = "lat") Double lat, @RequestParam(value = "lon") Double lon,
                                                             @RequestParam(value = "date", required = false, defaultValue = "#{(T(new java.util.Date()))}") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        return weatherService.getAstronomy(lat, lon, date);
    }

    @GetMapping("astronomy/city")
    public Mono<AstronomyResponse> getAstronomyByCity(@RequestParam(value = "city") String city,
                                                      @RequestParam(value = "date", required = false, defaultValue = "#{(T(new java.util.Date()))}") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        return weatherService.getAstronomyByCityOrIp(city, date);
    }

    @GetMapping("real/{ip}")
    public Mono<RealTimeResponse> getCurrentWeatherByIp(@PathVariable String ip) {
        return weatherService.getCurrentWeatherByCityOrIp(ip);
    }

    @GetMapping("astronomy/{ip}")
    public Mono<AstronomyResponse> getAstronomyByIp(@PathVariable String ip,
                                                    @RequestParam(value = "date", required = false, defaultValue = "#{(T(new java.util.Date()))}") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        return weatherService.getAstronomyByCityOrIp(ip, date);
    }
}
