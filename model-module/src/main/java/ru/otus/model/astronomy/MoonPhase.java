package ru.otus.model.astronomy;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * {@code MoonPhases} describes moon phase.
 */
public enum MoonPhase {
    NEW_MOON("New Moon"),
    WAXING_CRESCENT("Waxing Crescent"),
    FIRST_QUARTER("First Quarter"),
    WAXING_GIBBOUS("Waxing Gibbous"),
    FULL_MOON("Full Moon"),
    WANING_GIBBOUS("Waning Gibbous"),
    LAST_QUARTER("Last Quarter"),
    WANING_CRESCENT("Waning Crescent");

    private final String name;

    MoonPhase(String name) {
        this.name = name;
    }

    @JsonCreator
    public static MoonPhase getItem(String name) {
        for (MoonPhase aip : values()) {
            if (aip.getValue().equals(name)) {
                return aip;
            }
        }
        return null;
    }

    public String getValue() {
        return name;
    }
}
