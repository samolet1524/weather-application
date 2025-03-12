package ru.otus.frontapi.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FrontApiRequestParam implements Serializable {
    @Serial
    static final long serialVersionUID = 1L;

    String city;
    Double lat;
    Double lon;

    @AssertTrue(message = "Either of City or Lat and Lon need to be present")
    public boolean isValid() {
        return (city != null) || (lat != null && lon != null);
    }
}
