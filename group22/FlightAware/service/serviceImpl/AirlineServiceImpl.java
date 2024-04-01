package com.group22.FlightAware.service.serviceImpl;

import com.group22.FlightAware.dto.AirlineDto;
import com.group22.FlightAware.entity.Airline;
import com.group22.FlightAware.repository.AirlinesRepository;
import com.group22.FlightAware.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlinesRepository airlinesRepository;

    @Override
    public Long createAirLine(AirlineDto airlineDto) {
        return airlinesRepository.save(Airline.builder()
                                        .name(airlineDto.getName())
                                        .country(airlineDto.getCountry())
                                        .build()).
                                    getAirlinesId();
    }

    @Override
    public List<AirlineDto> getAllAirline() {
        return airlinesRepository.findAll().stream().map(this::toAirline).collect(Collectors.toList());
    }

    @Override
    public AirlineDto getAirlineById(Long id) {
        Optional<Airline> airline=airlinesRepository.findById(id);
        return airline.map(this::toAirline).orElse(null);
    }

    @Override
    public void updateAirline(AirlineDto airline) {
        if(airlinesRepository.existsById(airline.getAirlinesId())){
            airlinesRepository.save(toAirlineDto(airline));
        }
    }

    @Override
    public void deleteAirlineById(Long id) {
        if(airlinesRepository.existsById(id))
            airlinesRepository.deleteById(id);
    }

    private AirlineDto toAirline(Airline airline){
        return AirlineDto.builder().airlinesId(airline.getAirlinesId())
                .country(airline.getCountry())
                .name(airline.getName()).build();
    }

    private Airline toAirlineDto(AirlineDto airline){
        return Airline.builder()
                .airlinesId(airline.getAirlinesId())
                .name(airline.getName())
                .country(airline.getCountry())
                .build();
    }


}
