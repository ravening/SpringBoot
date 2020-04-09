package com.rakeshv.springdatamongodb.repositories;

import com.rakeshv.springdatamongodb.domains.FlightInformation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightInformationRepository extends ReactiveMongoRepository<FlightInformation, String> {
}
