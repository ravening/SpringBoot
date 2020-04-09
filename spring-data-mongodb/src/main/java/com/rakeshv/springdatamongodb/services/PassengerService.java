package com.rakeshv.springdatamongodb.services;

import com.rakeshv.springdatamongodb.domains.Passenger;
import com.rakeshv.springdatamongodb.repositories.PassengerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Slf4j
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository repository) {
        this.passengerRepository = repository;
    }

    public Flux<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
}
