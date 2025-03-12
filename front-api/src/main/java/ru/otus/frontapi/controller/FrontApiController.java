package ru.otus.frontapi.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.otus.frontapi.dto.FrontApiRequestParam;
import ru.otus.frontapi.service.FrontApiService;
import ru.otus.model.weather.RealTimeResponse;

import java.util.Date;
import java.util.Locale;

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
    public Mono<String> getDayLength(@Valid @ModelAttribute FrontApiRequestParam requestParam,
                                     @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        return frontApiService.getDayLength(requestParam, date)
                .map(duration -> String.format(Locale.forLanguageTag("ru-Ru"), "%d:%02d", duration.toHours(), duration.toMinutesPart()));
    }
}
