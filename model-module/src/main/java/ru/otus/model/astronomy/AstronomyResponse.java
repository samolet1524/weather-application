package ru.otus.model.astronomy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.otus.model.location.Location;

import java.util.Date;

/**
 * {@code AstronomyResponse} describes API astronomy response data
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AstronomyResponse {
    /**
     * {@link Location}
     */
    Location location;
    /**
     * {@link Astronomy}
     */
    Astronomy astronomy;
    /**
     * For internal usage
     */
    Date lastUpdated;
}
