package ru.otus.archiveservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.archiveservice.model.WeatherPoint;
import ru.otus.model.weather.RealTimeResponse;

/**
 * {@code WeatherPointMapper} configures the generation of bean mapping from {@link RealTimeResponse} to {@link WeatherPoint}.
 */
@Mapper(componentModel = "spring")
public interface WeatherPointMapper {
    /**
     * Method transforms {@link RealTimeResponse} object to {@link WeatherPoint} object.
     *
     * @param response source object
     * @return target object
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdated", source = "response.current.lastUpdated")
    @Mapping(target = "tempC", source = "response.current.tempC")
    @Mapping(target = "tempF", source = "response.current.tempF")
    @Mapping(target = "condition", source = "response.current.condition.text")
    @Mapping(target = "windDirection", source = "response.current.windDirection")
    @Mapping(target = "pressureMb", source = "response.current.pressureMb")
    @Mapping(target = "humidity", source = "response.current.humidity")
    @Mapping(target = "windKph", source = "response.current.windKph")
    @Mapping(target = "feelsLikeC", source = "response.current.feelsLikeC")
    @Mapping(target = "feelsLikeF", source = "response.current.feelsLikeF")
    @Mapping(target = "UVIndex", source = "response.current.UVIndex")
    @Mapping(target = "location", ignore = true)
    WeatherPoint toWeatherPoint(RealTimeResponse response);
}
