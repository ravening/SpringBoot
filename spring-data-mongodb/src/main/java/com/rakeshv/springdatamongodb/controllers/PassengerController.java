package com.rakeshv.springdatamongodb.controllers;

import com.rakeshv.springdatamongodb.domains.Passenger;
import com.rakeshv.springdatamongodb.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    @Autowired
    PassengerService passengerService;

    @GetMapping
    public Flux<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }
}
