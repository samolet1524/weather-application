package ru.otus.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.otus.model.astronomy.MoonPhase;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Astro {
    /**
     * Sunrise time
     */
    String sunrise;
    /**
     * Sunset time
     */
    String sunset;
    /**
     * Moonrise time
     */
    String moonrise;
    /**
     * Moon set time
     */
    String moonset;
    /**
     * Moon phases. {@link MoonPhase}
     */
    @JsonProperty("moon_phase")
    MoonPhase moonPhase;
}
