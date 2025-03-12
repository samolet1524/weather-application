package ru.otus.archiveservice.service;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@code CustomMetricsService} stores custom metrics
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CustomMetricsService {

    final MeterRegistry meterRegistry;

    @Getter
    AtomicDouble tempGauge;
    @Getter
    AtomicDouble feelsLikeGauge;
    @Getter
    AtomicDouble humidityGauge;
    @Getter
    AtomicLong dayLengthGauge;

    @PostConstruct
    public void init() {
        dayLengthGauge = meterRegistry.gauge("day.length", new AtomicLong(0));
        tempGauge = meterRegistry.gauge("temperature.point", new AtomicDouble(0));
        feelsLikeGauge = meterRegistry.gauge("temperature.point.feelsLike", new AtomicDouble(0));
        humidityGauge = meterRegistry.gauge("humidity.point", new AtomicDouble(0));
    }
}
