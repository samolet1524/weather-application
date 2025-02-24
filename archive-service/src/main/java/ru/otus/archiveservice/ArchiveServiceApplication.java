package ru.otus.archiveservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.otus.archiveservice.repository.AstronomyRepository;
import ru.otus.archiveservice.repository.LocationRepository;
import ru.otus.archiveservice.repository.WeatherPointRepository;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackageClasses = {WeatherPointRepository.class, LocationRepository.class, AstronomyRepository.class})
public class ArchiveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchiveServiceApplication.class, args);
    }

}
