package com.group22.FlightAware.service.serviceImpl;

import com.group22.FlightAware.dto.FlightPathDto;
import com.group22.FlightAware.entity.FlightPath;
import com.group22.FlightAware.repository.FlightPathRepository;
import com.group22.FlightAware.service.FlightPathService;
import com.group22.FlightAware.service.FlightPathService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Builder
public class FlightPathServiceImpl implements FlightPathService {

    @Autowired
    private FlightPathRepository flightpathRepository;

    @Override
    public Long createAirLine(FlightPathDto flightpathDto) {
        return flightpathRepository.save(FlightPath.builder()
                        .latitude(flightpathDto.getLatitude())
                        .longitude(flightpathDto.getLongitude())
                        .speed(flightpathDto.getSpeed())
                        .recordedTime(flightpathDto.getRecordedTime())
                        .altitude(flightpathDto.getAltitude())
                        .build()).
                getFlightPathId();
    }

    @Override
    public List<FlightPathDto> getAllFlightPath() {
        return flightpathRepository.findAll().stream().map(this::toFlightPath).collect(Collectors.toList());
    }

    @Override
    public FlightPathDto getFlightPathById(Long id) {
        Optional<FlightPath> flightpath=flightpathRepository.findById(id);
        return flightpath.map(this::toFlightPath).orElse(null);
    }

    @Override
    public void updateFlightPath(FlightPathDto flightpath) {
        if(flightpathRepository.existsById(flightpath.getFlightpathId())){
            flightpathRepository.save(toFlightPathDto(flightpath));
        }
    }

    @Override
    public void deleteFlightPathById(Long id) {
        if(flightpathRepository.existsById(id))
            flightpathRepository.deleteById(id);
    }

    private FlightPathDto toFlightPath(FlightPath flightpath){
        return FlightPathDto.builder().flightpathId(flightpath.getFlightPathId())
                .latitude(flightpath.getLatitude())
                .longitude(flightpath.getLongitude())
                .speed(flightpath.getSpeed())
                .altitude(flightpath.getAltitude())
                .recordedTime(flightpath.getRecordedTime()).build();
    }

    private FlightPath toFlightPathDto(FlightPathDto flightpath){
        return FlightPath.builder()
                .flightPathId(flightpath.getFlightpathId())
                .latitude(flightpath.getLatitude())
                .longitude(flightpath.getLongitude())
                .speed(flightpath.getSpeed())
                .altitude(flightpath.getAltitude())
                .recordedTime(flightpath.getRecordedTime())
                .build();
    }


}
