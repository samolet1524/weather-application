package ru.otus.archiveservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.archiveservice.model.Location;

/**
 * {@code LocationMapper} configures the generation of bean mapping from {@link ru.otus.model.location.Location} to {@link Location}.
 */
@Mapper(componentModel = "spring")
public interface LocationMapper {

    /**
     * Method transforms {@link ru.otus.model.location.Location} object to {@link Location} object.
     *
     * @param location source object
     * @return target object
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "weatherPoints", ignore = true)
    Location toLocation(ru.otus.model.location.Location location);
}
