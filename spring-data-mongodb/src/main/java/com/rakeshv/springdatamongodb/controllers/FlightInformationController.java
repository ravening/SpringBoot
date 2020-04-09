package com.rakeshv.springdatamongodb.controllers;

import com.rakeshv.springdatamongodb.domains.FlightInformation;
import com.rakeshv.springdatamongodb.services.FlightInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/flights")
public class FlightInformationController {
    @Autowired
    FlightInformationService flightInformationService;

    @GetMapping
    public Flux<FlightInformation> getFlightInformations() {
        return flightInformationService.getAllFlightInformation();
    }
}
