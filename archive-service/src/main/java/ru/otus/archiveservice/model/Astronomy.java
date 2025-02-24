package ru.otus.archiveservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "astronomies")
public class Astronomy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "check_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date date;
    /**
     * Sunrise time
     */
    @DateTimeFormat(pattern = "hh:mm:ss")
    Time sunrise;
    /**
     * Sunset time
     */
    @DateTimeFormat(pattern = "hh:mm:ss")
    Time sunset;
    /**
     * Moonrise time
     */
    @DateTimeFormat(pattern = "hh:mm:ss")
    Time moonrise;
    /**
     * Moon set time
     */
    @DateTimeFormat(pattern = "hh:mm:ss")
    Time moonset;
    /**
     * Moon phases. {@link MoonPhase}
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "moon_phase")
    MoonPhase moonPhase;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    Location location;
}
