package com.group22.FlightAware.repository;
import com.group22.FlightAware.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightsRepository extends JpaRepository<Flight,Long> {
}
