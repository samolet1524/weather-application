package ru.otus.archiveservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.archiveservice.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
