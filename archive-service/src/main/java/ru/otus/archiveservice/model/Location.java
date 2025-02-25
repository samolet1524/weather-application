package ru.otus.archiveservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * {@code Location} describes the location for which the information is stored in the database.
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "locations")
public class Location {

    /**
     * Auto generated primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(mappedBy="location")
    Set<WeatherPoint> weatherPoints;
    /**
     * Location name
     */
    String name;
    /**
     * Region or state of the location, if available
     */
    String region;
    /**
     * Location country
     */
    String country;
    /**
     * Latitude in decimal degree
     */
    Double lat;
    /**
     * Longitude in decimal degree
     */
    Double lon;
    /**
     * Time zone name
     */
    @JsonProperty("time_zone")
    String timezone;
}
