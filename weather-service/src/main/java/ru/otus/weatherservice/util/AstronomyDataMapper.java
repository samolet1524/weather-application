package ru.otus.weatherservice.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.otus.model.astronomy.Astronomy;
import ru.otus.weatherservice.model.Astro;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface AstronomyDataMapper {

    @Mapping(target = "sunrise", source = "astro.sunrise", qualifiedByName = "StringToTime")
    @Mapping(target = "sunset", source = "astro.sunset", qualifiedByName = "StringToTime")
    @Mapping(target = "moonrise", source = "astro.moonrise", qualifiedByName = "StringToTime")
    @Mapping(target = "moonset", source = "astro.moonset", qualifiedByName = "StringToTime")
    @Mapping(target = "moonPhase", source = "astro.moonPhase")
    Astronomy toAstronomy(Astro astro);

    @Named("StringToTime")
    default LocalTime toLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm a", Locale.US));
    }
}
