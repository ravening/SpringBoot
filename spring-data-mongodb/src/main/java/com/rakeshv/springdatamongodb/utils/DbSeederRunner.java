package com.rakeshv.springdatamongodb.utils;

import com.rakeshv.springdatamongodb.domains.Aircraft;
import com.rakeshv.springdatamongodb.domains.FlightInformation;
import com.rakeshv.springdatamongodb.domains.FlightType;
import com.rakeshv.springdatamongodb.domains.Passenger;
import com.rakeshv.springdatamongodb.services.FlightInformationService;
import com.rakeshv.springdatamongodb.services.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(1)
public class DbSeederRunner implements CommandLineRunner {
    private MongoTemplate mongoTemplate;

    @Autowired
    PassengerService passengerService;
    FlightInformationService flightInformationService;

    public DbSeederRunner(MongoTemplate mongoTemplate, FlightInformationService service) {
        this.mongoTemplate = mongoTemplate;
        this.flightInformationService = service;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application started");
//        empty();
        seed();
    }

    private void empty() {
//        this.mongoTemplate.remove(new Query(), FlightInformation.class);
        this.flightInformationService.deleteAll();
    }

    private void seed() {
        FlightInformation amsToBlr = FlightInformation.builder()
                .departureCity("Amsterdam")
                .destinationCity("Bengaluru")
                .type(FlightType.International)
                .durationMin(600)
                .aircraft(Aircraft.builder().model("747").nbSeats(250).build())
                .departureDate(LocalDateTime.of(2020, 1, 10, 10, 10, 0))
                .isDelayed(true).build();

        FlightInformation blrToAms = FlightInformation.builder()
                .departureCity("Bengaluru")
                .destinationCity("Amsterdam")
                .type(FlightType.International)
                .durationMin(540)
                .aircraft(Aircraft.builder().model("747").nbSeats(250).build())
                .departureDate(LocalDateTime.of(2020, 2, 20, 7, 25, 0))
                .isDelayed(false).build();

        FlightInformation blrToDelhi = FlightInformation.builder()
                .destinationCity("Delhi")
                .departureCity("Bengaluru")
                .type(FlightType.Internal)
                .durationMin(180)
                .aircraft(Aircraft.builder().model("737").nbSeats(100).build())
                .departureDate(LocalDateTime.of(2020, 3, 18, 11, 45, 0))
                .isDelayed(true).build();

        FlightInformation lasToSfo = FlightInformation.builder()
                .destinationCity("Las Vegas")
                .destinationCity("San Francisco")
                .type(FlightType.Internal)
                .durationMin(120)
                .aircraft(Aircraft.builder().model("A319").nbSeats(150).build())
                .departureDate(LocalDateTime.of(2020, 4, 3, 14, 55, 0))
                .isDelayed(true).build();

        FlightInformation sfoToAms = FlightInformation.builder()
                .destinationCity("Amsterdam")
                .departureCity("San Francisco")
                .departureDate(LocalDateTime.of(2020, 5, 15, 10, 35, 0))
                .durationMin(660)
                .aircraft(Aircraft.builder().model("747").nbSeats(300).build())
                .type(FlightType.International)
                .isDelayed(false).build();

        List<FlightInformation> flightInformations = Arrays.asList(
                blrToAms,
                amsToBlr,
                blrToDelhi,
                lasToSfo,
                sfoToAms
        );

//        this.mongoTemplate.insertAll(flightInformations);

        this.flightInformationService.saveAll(
                Flux.just(blrToAms, amsToBlr, blrToDelhi, lasToSfo, sfoToAms)
        ).subscribe();

        Mono<Long> count = this.flightInformationService.getNumberOfFlights();
        count.subscribe(value -> {
            log.info("Number of flights are {}", value);
        });
        Passenger passenger1 = Passenger.builder()
                .firstName("Rakesh")
                .lastName("Venkatesh")
                .age(33)
                .nationality("Indian").build();

        Passenger passenger2 = Passenger.builder()
                .firstName("Venkatesh")
                .lastName("Rakesh")
                .age(16)
                .nationality("Indian").build();

        Passenger passenger3 = Passenger.builder()
                .firstName("John")
                .lastName("Doe")
                .age(60)
                .nationality("American").build();

        List<Passenger> passengers = Arrays.asList(
                passenger1,
                passenger2,
                passenger3
        );

        this.mongoTemplate.insertAll(passengers);

        log.info("Passengers are");
        Flux<Passenger> passengerFlux = passengerService.getAllPassengers();
        passengerFlux.subscribe(passenger -> {
            log.info("{}", passenger);
        });

        log.info("======");
        log.info("Flight informations are");
        Flux<FlightInformation> flightInformationFlux = flightInformationService.getAllFlightInformation();
        flightInformationFlux.subscribe(flightInformation1 -> {
            log.info("{}", flightInformation1);
        });
    }
}
