package com.rakeshv.springdatamongodb.controllers;

import com.rakeshv.springdatamongodb.domains.Passenger;
import com.rakeshv.springdatamongodb.services.PassengerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/query")
public class PassengerQueryController {
    @Autowired
    PassengerQueryService queryService;

    @GetMapping("/adults")
    public ResponseEntity<List<Passenger>> getAdultPassengers() {
        return new ResponseEntity<>(queryService.getAdultPassengers(), HttpStatus.OK);
    }

    @GetMapping("/minors")
    public ResponseEntity<List<Passenger>> getMinorPassengers() {
        return new ResponseEntity<>(queryService.getMinorPassengers(), HttpStatus.OK);
    }
}
