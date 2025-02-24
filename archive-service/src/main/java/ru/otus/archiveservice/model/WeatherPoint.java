package ru.otus.archiveservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "points")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    Location location;
    /**
     * Local time when the real time data was updated
     */
    @Column(name = "last_updated")
    String lastUpdated;
    /**
     * Temperature in celsius
     */
    @Column(name = "temp_c")
    Double tempC;
    /**
     * Temperature in fahrenheit
     */
    @Column(name = "temp_f")
    Double tempF;
    /**
     * Weather condition as text
     */
    String condition;
    /**
     * Wind speed in kilometer per hour
     */
    @Column(name = "wind_kph")
    Double windKph;
    /**
     * Wind direction as 16 point compass
     */
    @Column(name = "wind_dir")
    String windDirection;
    /**
     * Pressure in millibars
     */
    @Column(name = "pressure_mb")
    Double pressureMb;
    /**
     * Humidity as percentage
     */
    Integer humidity;
    /**
     * Feels like temperature in Celsius
     */
    @Column(name = "feelslike_c")
    Double feelsLikeC;
    /**
     * Feels like temperature in Fahrenheit
     */
    @Column(name = "feelslike_f")
    Double feelsLikeF;
    /**
     * UV Index
     */
    @Column(name = "uv_index")
    Double UVIndex;

}
