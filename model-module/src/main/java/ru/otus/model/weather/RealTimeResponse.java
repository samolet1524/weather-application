package ru.otus.model.weather;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.otus.model.location.Location;

/**
 * {@code RealTimeResponse} describes API real time weather response data
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
