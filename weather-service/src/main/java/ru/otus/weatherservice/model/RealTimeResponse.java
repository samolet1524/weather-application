package ru.otus.weatherservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * {@code RealTimeResponse} describes API response data
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RealTimeResponse {
    /**
     * {@link Location}
     */
    Location location;
    /**
     * {@link CurrentWeatherData}
     */
    CurrentWeatherData current;
}
