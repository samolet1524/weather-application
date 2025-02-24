package ru.otus.archiveservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.archiveservice.model.WeatherPoint;

public interface WeatherPointRepository extends JpaRepository<WeatherPoint, Long> {
}
