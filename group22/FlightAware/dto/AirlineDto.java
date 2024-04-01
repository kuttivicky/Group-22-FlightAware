package com.group22.FlightAware.dto;

import com.group22.FlightAware.entity.Aircraft;
import com.group22.FlightAware.entity.Airline;
import com.group22.FlightAware.entity.Flight;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineDto {
    private Long airlinesId;
    private String name;
    private String country;
}
