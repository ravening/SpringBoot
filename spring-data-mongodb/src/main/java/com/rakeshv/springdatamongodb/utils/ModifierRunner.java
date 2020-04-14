package com.rakeshv.springdatamongodb.utils;

import com.rakeshv.springdatamongodb.domains.FlightInformation;
import com.rakeshv.springdatamongodb.services.FlightInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Order(2)
public class ModifierRunner implements CommandLineRunner {
    private MongoTemplate mongoTemplate;

    public ModifierRunner(MongoTemplate template) {
        this.mongoTemplate = template;
    }

    @Autowired
    FlightInformationService flightInformationService;

    @Override
    public void run(String... args) throws Exception {
//        markAllFlightsToAmsAsDelayed();
    }

    void markAllFlightsToAmsAsDelayed() {
        Query departingFromAms = Query.query(
                Criteria.where("destination").is("Amsterdam")
        );

        Update setDelayed = Update.update("isDelayed", true);

        this.mongoTemplate.updateMulti(
                departingFromAms,
                setDelayed,
                FlightInformation.class);
    }

    void removeFlightsWithLessDuration() {
        Query query = Query.query(
                Criteria.where("duration").lt(120)
        );

        this.mongoTemplate.findAllAndRemove(query, FlightInformation.class);
    }


    private void delayFlight(String departureCity, int duration) {
        Flux<FlightInformation> flightInformation = this.flightInformationService.getFlightsByDepartureCity(departureCity);

        flightInformation.subscribe(flight -> {
            flight.setDurationMin(flight.getDurationMin() + duration);
        });
        this.flightInformationService.saveAll((flightInformation));
    }

    public List<FlightInformation> findByDepartureAndDestination(String departure, String destination) {
        return this.flightInformationService.getByDepartureAndDestinationCity(departure, destination);
    }

    public List<FlightInformation> findByMinSeats(int seat) {
        return this.flightInformationService.getFlightsByMinSeat(seat);
    }
}
