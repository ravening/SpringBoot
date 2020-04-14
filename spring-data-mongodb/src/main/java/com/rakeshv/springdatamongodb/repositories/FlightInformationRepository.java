package com.rakeshv.springdatamongodb.repositories;

import com.rakeshv.springdatamongodb.domains.FlightInformation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface FlightInformationRepository extends ReactiveMongoRepository<FlightInformation, String> {
    Flux<FlightInformation> findByDepartureCityEquals(String name);

    List<FlightInformation> findByDepartureCityAndDestinationCityEqualsIgnoreCase(String departure, String destination);

    @Query("{'aircraft.nbSeats' : {$gte : ?0}}")
    List<FlightInformation> findByMinSeats(int seat);
}
