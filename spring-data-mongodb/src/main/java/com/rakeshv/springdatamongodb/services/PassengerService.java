package com.rakeshv.springdatamongodb.services;

import com.rakeshv.springdatamongodb.domains.Passenger;
import com.rakeshv.springdatamongodb.repositories.PassengerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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

    public Flux<Passenger> getAll() {
        return this.passengerRepository.findAll();
    }

    public Flux<Passenger> getAdultPassengers() {
        return this.passengerRepository.findByAgeBetween(18, 58);
    }

    public Flux<Passenger> getMinorPassengers() {
        return this.passengerRepository.findMinors(18);
    }

    public Flux<Passenger> getSeniorPassengers() {
        return this.passengerRepository.findSeniors(58);
    }
}
