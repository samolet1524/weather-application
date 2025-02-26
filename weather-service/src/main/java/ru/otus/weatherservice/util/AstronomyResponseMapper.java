package ru.otus.weatherservice.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.model.astronomy.AstronomyResponse;
import ru.otus.weatherservice.model.AstronomyAPIResponse;

@Mapper(componentModel = "spring", uses = AstronomyDataMapper.class)
public interface AstronomyResponseMapper {

    @Mapping(target = "astronomy", source = "apiResponse.astronomy.astro")
    AstronomyResponse toAstronomyResponse(AstronomyAPIResponse apiResponse);
}
