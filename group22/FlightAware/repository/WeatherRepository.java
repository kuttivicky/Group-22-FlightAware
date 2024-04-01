package com.group22.FlightAware.repository;
import com.group22.FlightAware.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather,Long> {
}
