package com.group22.FlightAware.service;

import com.group22.FlightAware.dto.AirlineDto;

import java.util.List;

public interface AirlineService {
     Long createAirLine(AirlineDto airlineDto);

     List<AirlineDto> getAllAirline();

     AirlineDto getAirlineById(Long id);

     void updateAirline(AirlineDto airline);

     void deleteAirlineById(Long id);
}
