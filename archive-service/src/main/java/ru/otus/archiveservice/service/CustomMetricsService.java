package ru.otus.archiveservice.service;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@code CustomMetricsService} stores custom metrics
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomMetricsService {

    MeterRegistry meterRegistry;

    public void registerWeatherValueTemperature(String tag, Double value) {
        AtomicDouble atomicDouble = new AtomicDouble(value);
        Gauge.builder("temperature.point", () -> atomicDouble)
                .tags("temperature", tag)
                .register(meterRegistry);
    }

    public void registerWeatherValueHumidity(Double value) {
        AtomicDouble atomicDouble = new AtomicDouble(value);
        Gauge.builder("humidity.point", () -> atomicDouble)
                .register(meterRegistry);
    }

    public void registerAstronomy(Long value) {
        AtomicLong atomicLong = new AtomicLong(value);
        Gauge.builder("day.length",() -> atomicLong)
                .register(meterRegistry);
    }
}
