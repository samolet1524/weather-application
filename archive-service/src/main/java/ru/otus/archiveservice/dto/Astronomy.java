package ru.otus.archiveservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.otus.archiveservice.model.MoonPhase;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Astronomy {
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
