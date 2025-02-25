package ru.otus.archiveservice.service;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * {@code CustomMetricsService} stores custom metrics
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CustomMetricsService {

    final MeterRegistry meterRegistry;
    @Getter
    DistributionSummary summaryTemperature;
    @Getter
    DistributionSummary summaryHumidity;
    @Getter
    DistributionSummary summaryDayLength;

    @PostConstruct
    public void init() {
        summaryTemperature = DistributionSummary
                .builder("temperature")
                .baseUnit("degree").register(meterRegistry);
        summaryHumidity = DistributionSummary
                .builder("humidity")
                .baseUnit("percent").register(meterRegistry);
        summaryDayLength = DistributionSummary
                .builder("day.length")
                .baseUnit("minutes").register(meterRegistry);

    }
}
