package ru.otus.archiveservice.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * {@code RealTimeResponse} describes API real time weather response data
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RealTimeResponse {
    /**
     * {@link LocationDto}
     */
    LocationDto location;
    /**
     * {@link CurrentWeatherData}
     */
    CurrentWeatherData current;
}
