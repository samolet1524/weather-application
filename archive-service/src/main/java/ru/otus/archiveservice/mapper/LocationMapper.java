package ru.otus.archiveservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.archiveservice.dto.LocationDto;
import ru.otus.archiveservice.model.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "weatherPoints", ignore = true)
    Location toLocation(LocationDto location);
}
