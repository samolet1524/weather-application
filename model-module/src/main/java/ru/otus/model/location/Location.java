package ru.otus.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * {@code Location} describes the matched location for which the information has been returned.
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Location {

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
    @JsonProperty("tz_id")
    String timezone;
}
