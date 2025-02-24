package ru.otus.archiveservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.archiveservice.model.WeatherPoint;

@Repository
public interface WeatherPointRepository extends JpaRepository<WeatherPoint, Long> {
}
