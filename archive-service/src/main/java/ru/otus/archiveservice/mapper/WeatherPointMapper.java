package ru.otus.archiveservice.mapper;

import org.mapstruct.Mapper;
import ru.otus.archiveservice.dto.RealTimeResponse;
import ru.otus.archiveservice.model.Location;
import ru.otus.archiveservice.model.WeatherPoint;

@Mapper(componentModel = "spring", uses = Location.class)
public interface WeatherPointMapper {

    WeatherPoint toWeatherPoint(RealTimeResponse realTimeResponse);
}
