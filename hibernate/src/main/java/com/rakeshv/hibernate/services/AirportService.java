package com.rakeshv.hibernate.services;

import com.rakeshv.hibernate.models.Airport;
import com.rakeshv.hibernate.repositories.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private AirportRepository airportRepository;

    public void saveAirport(Airport airport) {
        this.airportRepository.save(airport);
    }

    public void saveAirportList(List<Airport> airportList) {
        this.airportRepository.saveAll(airportList);
    }

    public Optional<Airport> getAirportById(Long id) {
        return this.airportRepository.findById(id);
    }

    public Optional<Airport> getAirportByName(String name) {
        return this.airportRepository.findByNameEqualsIgnoreCase(name);
    }

    public List<Airport> getAllAirports() {
        return this.airportRepository.findAll();
    }

}
