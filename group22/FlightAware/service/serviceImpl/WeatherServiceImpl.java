package com.group22.FlightAware.service.serviceImpl;

import com.group22.FlightAware.dto.WeatherDto;
import com.group22.FlightAware.entity.Weather;
import com.group22.FlightAware.repository.WeatherRepository;
import com.group22.FlightAware.service.WeatherService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Builder
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public Long createWeather(WeatherDto weatherDto) {
        return weatherRepository.save(Weather.builder()
                        .visibility(weatherDto.getVisibility())
                        .windDirection(weatherDto.getWindDirection())
                        .windSpeed(weatherDto.getWindSpeed())
                        .timeStamp(weatherDto.getTimeStamp())
                        .build()).
                getWeatherId();
    }

    @Override
    public List<WeatherDto> getAllWeather() {
        return weatherRepository.findAll().stream().map(this::toWeather).collect(Collectors.toList());
    }

    @Override
    public WeatherDto getWeatherById(Long id) {
        Optional<Weather> weather=weatherRepository.findById(id);
        return weather.map(this::toWeather).orElse(null);
    }

    @Override
    public void updateWeather(WeatherDto weather) {
        if(weatherRepository.existsById(weather.getWeatherId())){
            weatherRepository.save(toWeatherDto(weather));
        }
    }

    @Override
    public void deleteWeatherById(Long id) {
        if(weatherRepository.existsById(id))
            weatherRepository.deleteById(id);
    }

    private WeatherDto toWeather(Weather weather){
        return WeatherDto.builder().weatherId(weather.getWeatherId())
                .visibility(weather.getVisibility())
                .windDirection(weather.getWindDirection())
                .windSpeed(weather.getWindSpeed())
                .timeStamp(weather.getTimeStamp()).build();
    }

    private Weather toWeatherDto(WeatherDto weather){
        return Weather.builder()
                .visibility(weather.getVisibility())
                .windDirection(weather.getWindDirection())
                .windSpeed(weather.getWindSpeed())
                .timeStamp(weather.getTimeStamp())
                .build();
    }


}
