package com.rakeshv.springdatamongodb.services;

import com.rakeshv.springdatamongodb.domains.FlightInformation;
import com.rakeshv.springdatamongodb.repositories.FlightInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class FlightInformationService {
    private final FlightInformationRepository flightInformationRepository;

    public FlightInformationService(FlightInformationRepository repository) {
        this.flightInformationRepository = repository;
    }

    public Flux<FlightInformation> getAllFlightInformation() {
        return flightInformationRepository.findAll(Sort.by("departureCity").ascending());
    }

    public Flux<FlightInformation> insert(Flux<FlightInformation> flightInformations) {
        return this.flightInformationRepository.insert(flightInformations);
    }

    public void deleteAll() {
        this.flightInformationRepository.deleteAll();
    }

    public Flux<FlightInformation> getFlightsByDepartureCity(String city) {
        return this.flightInformationRepository.findByDepartureCityEquals(city);
    }

    public Flux<FlightInformation> saveAll(Flux<FlightInformation> flightInformations) {
        return this.flightInformationRepository.saveAll(flightInformations);
    }

    public List<FlightInformation> getByDepartureAndDestinationCity(String departure, String destination) {
        return this.flightInformationRepository.findByDepartureCityAndDestinationCityEqualsIgnoreCase(departure, destination);
    }

    public List<FlightInformation> getFlightsByMinSeat(int seat) {
        return this.flightInformationRepository.findByMinSeats(seat);
    }

    public Mono<Long> getNumberOfFlights() {
        return this.flightInformationRepository.count();
    }
}
