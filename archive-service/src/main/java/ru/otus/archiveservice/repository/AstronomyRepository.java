package ru.otus.archiveservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.archiveservice.model.Astronomy;

public interface AstronomyRepository extends JpaRepository<Astronomy, Long> {
}
