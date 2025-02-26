package ru.otus.archiveservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.otus.archiveservice.model.Astronomy;

import java.sql.Time;
import java.time.LocalTime;

/**
 * {@code AstronomyMapper} configures the generation of bean mapping from {@link ru.otus.model.astronomy.Astronomy} to {@link Astronomy}.
 */
@Mapper(componentModel = "spring")
public interface AstronomyMapper {

    /**
     * Method transforms {@link ru.otus.model.astronomy.Astronomy} object to {@link Astronomy} object.
     *
     * @param astronomyDto source object
     * @return target object
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", expression = "java(new java.sql.Date(java.lang.System.currentTimeMillis()))")
    @Mapping(target = "sunrise", qualifiedByName = "transformTime", source = "astronomyDto.sunrise")
    @Mapping(target = "sunset", qualifiedByName = "transformTime", source = "astronomyDto.sunset")
    @Mapping(target = "moonrise", qualifiedByName = "transformTime", source = "astronomyDto.moonrise")
    @Mapping(target = "moonset", qualifiedByName = "transformTime", source = "astronomyDto.moonset")
    @Mapping(target = "location", ignore = true)
    Astronomy toAstronomy(ru.otus.model.astronomy.Astronomy astronomyDto);

    /**
     * Method transforms {@link LocalTime} object to {@link Time} object.
     *
     * @param time {@link LocalTime} object from source
     * @return target {@link Time} object
     */
    @Named("transformTime")
    default Time toSqlTime(LocalTime time) {
        long epochMilli = time.atDate(java.time.LocalDate.EPOCH)
                .atZone(java.time.ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        return new java.sql.Time(epochMilli);
    }
}
