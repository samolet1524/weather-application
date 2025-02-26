package ru.otus.frontapi.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.otus.frontapi.dto.FrontApiRequestParam;
import ru.otus.frontapi.service.FrontApiService;
import ru.otus.model.weather.RealTimeResponse;

import java.util.Date;

@RestController
@RequestMapping("api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FrontApiController {

    FrontApiService frontApiService;

    @GetMapping("weather")
    public Mono<RealTimeResponse> getWeather(@Valid @ModelAttribute FrontApiRequestParam requestParam) {
        return frontApiService.getWeather(requestParam);
    }

    @GetMapping("daylength")
    public Mono<Double> getDayLength(@Valid @ModelAttribute FrontApiRequestParam requestParam, @RequestParam(value = "date", required = false) Date date) {
        return frontApiService.getDayLength(requestParam, date);
    }
}
