package ru.otus.archiveservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.otus.archiveservice.model.MoonPhase;

import java.time.LocalTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AstronomyDto {
    /**
     * Sunrise time
     */
    LocalTime sunrise;
    /**
     * Sunset time
     */
    LocalTime sunset;
    /**
     * Moonrise time
     */
    LocalTime moonrise;
    /**
     * Moon set time
     */
    LocalTime moonset;
    /**
     * Moon phases. {@link MoonPhase}
     */
    @JsonProperty("moon_phase")
    MoonPhase moonPhase;
}
