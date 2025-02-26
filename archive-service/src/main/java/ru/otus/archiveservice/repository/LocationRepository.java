package ru.otus.archiveservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.archiveservice.model.Location;

import java.util.Optional;

/**
 * Data Access Object for {@link Location} entity
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByNameAndCountry(String name, String country);
}
