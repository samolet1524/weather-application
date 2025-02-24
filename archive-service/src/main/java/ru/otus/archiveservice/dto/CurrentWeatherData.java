package ru.otus.archiveservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * {@code CurrentWeatherData} describes current weather information
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CurrentWeatherData {
    /**
     * Local time when the real time data was updated
     */
    @JsonProperty("last_updated")
    String lastUpdated;
    /**
     * Temperature in celsius
     */
    @JsonProperty("temp_c")
    Double tempC;
    /**
     * Temperature in fahrenheit
     */
    @JsonProperty("temp_f")
    Double tempF;
    /**
     * Weather condition as text
     */
    Condition condition;
    /**
     * Wind speed in kilometer per hour
     */
    @JsonProperty("wind_kph")
    Double windKph;
    /**
     * Wind direction as 16 point compass
     */
    @JsonProperty("wind_dir")
    String windDirection;
    /**
     * Pressure in millibars
     */
    @JsonProperty("pressure_mb")
    Double pressureMb;
    /**
     * Humidity as percentage
     */
    @JsonProperty("humidity")
    Integer humidity;
    /**
     * Feels like temperature in Celsius
     */
    @JsonProperty("feelslike_c")
    Double feelsLikeC;
    /**
     * Feels like temperature in Fahrenheit
     */
    @JsonProperty("feelslike_f")
    Double feelsLikeF;
    /**
     * UV Index
     */
    @JsonProperty("uv")
    Double UVIndex;
}
