package ru.otus.archiveservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.archiveservice.model.Astronomy;

@Repository
public interface AstronomyRepository extends JpaRepository<Astronomy, Long> {
}
