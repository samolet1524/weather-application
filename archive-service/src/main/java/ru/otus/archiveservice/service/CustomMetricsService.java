package ru.otus.archiveservice.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * {@code CustomMetricsService} stores custom metrics
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomMetricsService {

    MeterRegistry meterRegistry;

    public void registerWeatherValue(String tag, Double value) {
        Counter.builder("archive").tag("temperature", tag).baseUnit("degree").register(meterRegistry).increment(value);
    }

    public void registerAstronomy(String tag, Long value) {
        Counter.builder("archive").tag("day.length", tag).baseUnit("minutes").register(meterRegistry).increment(value);
    }
}
