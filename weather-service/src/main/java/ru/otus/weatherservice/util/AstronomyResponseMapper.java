package ru.otus.weatherservice.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.weatherservice.model.AstronomyAPIResponse;
import ru.otus.weatherservice.model.AstronomyResponse;

@Mapper(componentModel = "spring", uses = AstronomyDataMapper.class)
public interface AstronomyResponseMapper {

    @Mapping(target = "astronomy", source = "apiResponse.astronomy.astro")
    AstronomyResponse toAstronomyResponse(AstronomyAPIResponse apiResponse);
}
