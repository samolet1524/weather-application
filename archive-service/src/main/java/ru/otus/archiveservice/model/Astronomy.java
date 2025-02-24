package ru.otus.archiveservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

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
    Date date;
    /**
     * Sunrise time
     */
    Time sunrise;
    /**
     * Sunset time
     */
    Time sunset;
    /**
     * Moonrise time
     */
    Time moonrise;
    /**
     * Moon set time
     */
    Time moonset;
    /**
     * Moon phases. {@link MoonPhase}
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "moon_phase")
    MoonPhase moonPhase;
}
