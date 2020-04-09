package com.rakeshv.springdatamongodb.services;

import com.rakeshv.springdatamongodb.domains.FlightInformation;
import com.rakeshv.springdatamongodb.repositories.FlightInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class FlightInformationService {
    private final FlightInformationRepository flightInformationRepository;

    public FlightInformationService(FlightInformationRepository repository) {
        this.flightInformationRepository = repository;
    }

    public Flux<FlightInformation> getAllFlightInformation() {
        return flightInformationRepository.findAll();
    }
}
