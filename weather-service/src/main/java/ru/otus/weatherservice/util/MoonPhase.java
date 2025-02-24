package ru.otus.weatherservice.util;

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
}
