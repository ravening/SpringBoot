package com.rakeshv.springdatamongodb;

import com.rakeshv.springdatamongodb.domains.Aircraft;
import com.rakeshv.springdatamongodb.domains.FlightInformation;
import com.rakeshv.springdatamongodb.domains.FlightType;
import com.rakeshv.springdatamongodb.domains.Passenger;
import com.rakeshv.springdatamongodb.services.FlightInformationService;
import com.rakeshv.springdatamongodb.services.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {
    private MongoTemplate mongoTemplate;

    @Autowired
    PassengerService passengerService;
    @Autowired
    FlightInformationService flightInformationService;

    public ApplicationRunner(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Application started");

        FlightInformation flightInformation = FlightInformation.builder()
                .departureCity("Amsterdam")
                .destinationCity("Bengaluru")
                .type(FlightType.International)
                .isDelayed(true).build();
        this.mongoTemplate.save(flightInformation);


        Passenger passenger = Passenger.builder()
                .firstName("Rakesh")
                .lastName("Venkatesh")
                .age(33)
                .nationality("Indian").build();

        this.mongoTemplate.save(passenger);

        Thread.sleep(5000);

        log.info("Passengers are");
        Flux<Passenger> passengerFlux = passengerService.getAllPassengers();
        passengerFlux.subscribe(passenger1 -> {
            log.info("{}", passenger1);
        });

        log.info("======");
        log.info("Flight informations are");
        Flux<FlightInformation> flightInformationFlux = flightInformationService.getAllFlightInformation();
        flightInformationFlux.subscribe(flightInformation1 -> {
            log.info("{}", flightInformation1);
        });
    }
}
