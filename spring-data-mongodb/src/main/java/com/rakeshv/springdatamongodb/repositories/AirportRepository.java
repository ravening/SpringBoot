package com.rakeshv.springdatamongodb.repositories;

import com.rakeshv.springdatamongodb.domains.Airport;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends ReactiveMongoRepository<Airport, String> {
}
