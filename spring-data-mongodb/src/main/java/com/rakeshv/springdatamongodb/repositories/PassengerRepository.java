package com.rakeshv.springdatamongodb.repositories;

import com.rakeshv.springdatamongodb.domains.Passenger;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends ReactiveMongoRepository<Passenger, String> {
}
