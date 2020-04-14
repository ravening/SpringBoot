package com.rakeshv.springdatamongodb.repositories;

import com.rakeshv.springdatamongodb.domains.Passenger;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PassengerRepository extends ReactiveMongoRepository<Passenger, String> {
    @Query("{'age' : {$lte : ?0}}")
    Flux<Passenger> findMinors(int age);

    @Query("{'age' : {$gte : ?0}}")
    Flux<Passenger> findSeniors(int age);

    Flux<Passenger> findByAgeBetween(int min, int max);
}
