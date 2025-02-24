package ru.otus.archiveservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.archiveservice.model.WeatherPoint;
import ru.otus.archiveservice.repository.LocationRepository;
import ru.otus.archiveservice.repository.WeatherPointRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ArchiveService {

    WeatherPointRepository weatherPointRepository;
    LocationRepository locationRepository;


    public void addPointToArchive(WeatherPoint weatherPoint) {

        weatherPointRepository.save(weatherPoint);
    }
}
