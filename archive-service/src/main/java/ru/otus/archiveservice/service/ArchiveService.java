package ru.otus.archiveservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.archiveservice.mapper.AstronomyMapper;
import ru.otus.archiveservice.mapper.LocationMapper;
import ru.otus.archiveservice.mapper.WeatherPointMapper;
import ru.otus.archiveservice.model.Astronomy;
import ru.otus.archiveservice.model.WeatherPoint;
import ru.otus.archiveservice.repository.AstronomyRepository;
import ru.otus.archiveservice.repository.LocationRepository;
import ru.otus.archiveservice.repository.WeatherPointRepository;
import ru.otus.model.astronomy.AstronomyResponse;
import ru.otus.model.weather.RealTimeResponse;

import java.time.Duration;

/**
 * Service to save data to the database
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ArchiveService {

    WeatherPointRepository weatherPointRepository;
    LocationRepository locationRepository;
    AstronomyRepository astronomyRepository;

    AstronomyMapper astronomyMapper;
    WeatherPointMapper weatherPointMapper;
    LocationMapper locationMapper;
    CustomMetricsService metricsService;


    /**
     * Saves received weather information to the database.
     *
     * @param response {@link RealTimeResponse} data
     */
    public void addWeatherPointToArchive(RealTimeResponse response) {
        WeatherPoint weatherPoint = weatherPointMapper.toWeatherPoint(response);
        locationRepository.findByNameAndCountry(response.getLocation().getName(), response.getLocation().getCountry())
                .ifPresentOrElse(weatherPoint::setLocation,
                        () -> weatherPoint.setLocation(locationRepository.save(locationMapper.toLocation(response.getLocation()))));
        weatherPointRepository.save(weatherPoint);
        metricsService.getSummaryTemperature().record(weatherPoint.getTempC());
        metricsService.getSummaryHumidity().record(weatherPoint.getHumidity());
    }

    /**
     * Saves received information about sunrise, sunset, moonrise, moonset, moon phase to the database.
     *
     * @param response {@link AstronomyResponse} data
     */
    public void addAstronomyPointToArchive(AstronomyResponse response) {
        Astronomy astronomy = astronomyMapper.toAstronomy(response.getAstronomy());
        locationRepository.findByNameAndCountry(response.getLocation().getName(), response.getLocation().getCountry())
                .ifPresentOrElse(astronomy::setLocation,
                        () -> astronomy.setLocation(locationRepository.save(locationMapper.toLocation(response.getLocation()))));
        astronomyRepository.save(astronomy);
        metricsService.getSummaryDayLength().record(Duration.between(astronomy.getSunrise().toLocalTime(), astronomy.getSunset().toLocalTime()).toMinutes());
    }
}
