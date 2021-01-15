package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByNameEqualsIgnoreCase(String name);
}
