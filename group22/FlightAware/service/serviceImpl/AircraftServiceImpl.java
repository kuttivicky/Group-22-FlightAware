package com.group22.FlightAware.service.serviceImpl;

import com.group22.FlightAware.dto.AircraftDto;
import com.group22.FlightAware.entity.Aircraft;
import com.group22.FlightAware.repository.AircraftRepository;
import com.group22.FlightAware.service.AircraftService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Builder
public class AircraftServiceImpl implements AircraftService {

    @Autowired
    private AircraftRepository aircraftsRepository;

    @Override
    public Long createAircraft(AircraftDto aircraftDto) {
        return aircraftsRepository.save(Aircraft.builder()
                        .type(aircraftDto.getType())
                        .build()).
                getAircraftId();
    }

    @Override
    public List<AircraftDto> getAllAircraft() {
        return aircraftsRepository.findAll().stream().map(this::toAircraft).collect(Collectors.toList());
    }

    @Override
    public AircraftDto getAircraftById(Long id) {
        Optional<Aircraft> aircraft=aircraftsRepository.findById(id);
        return aircraft.map(this::toAircraft).orElse(null);
    }

    @Override
    public void updateAircraft(AircraftDto aircraft) {
        if(aircraftsRepository.existsById(aircraft.getAircraftId())){
            aircraftsRepository.save(toAircraftDto(aircraft));
        }
    }

    @Override
    public void deleteAircraftById(Long id) {
        if(aircraftsRepository.existsById(id))
            aircraftsRepository.deleteById(id);
    }

    private AircraftDto toAircraft(Aircraft aircraft){
        return AircraftDto.builder().aircraftId(aircraft.getAircraftId())
                .type(aircraft.getType()).build();
    }

    private Aircraft toAircraftDto(AircraftDto aircraft){
        return Aircraft.builder()
                .aircraftId(aircraft.getAircraftId())
                .type(aircraft.getType())
                .build();
    }


}
