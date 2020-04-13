package com.rakeshv.springdatamongodb.services;

import com.rakeshv.springdatamongodb.domains.Passenger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PassengerQueryService {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Passenger> getAdultPassengers() {
        Query query = Query
                .query(Criteria.where("age").gt(18).lt(58));

        return mongoTemplate.find(query, Passenger.class);
    }

    public List<Passenger> getMinorPassengers() {
        Query query = Query
                .query(Criteria.where("age").lt(18));

        return mongoTemplate.find(query, Passenger.class);
    }

    public List<Passenger> getSeniorPassengers() {
        Query query = Query
                .query(Criteria.where("age").gt(58));

        return mongoTemplate.find(query, Passenger.class);
    }
}
