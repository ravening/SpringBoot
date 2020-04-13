package com.rakeshv.springdatamongodb.services;

import com.rakeshv.springdatamongodb.domains.FlightInformation;
import com.rakeshv.springdatamongodb.domains.FlightType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightQueryService {
    private MongoTemplate mongoTemplate;

    public FlightQueryService(MongoTemplate template) {
        this.mongoTemplate = template;
    }

    public List<FlightInformation> findAll(String field,
                                           int pageNb,
                                           int pageSize) {
        Query query = new Query()
                .with(Sort.by(Sort.Direction.ASC, field))
                .with(PageRequest.of(pageNb, pageSize));

        return this.mongoTemplate.find(query, FlightInformation.class);
    }

    public FlightInformation findSingleById(String id) {
        return this.mongoTemplate.findById(id, FlightInformation.class);
    }

    public long countInternational() {
        Query international = Query.query(Criteria.where("type")
                .is(FlightType.International));

        return this.mongoTemplate.count(international, FlightInformation.class);
    }

    public long countDomestic() {
        Query domestic = Query.query(Criteria.where("type")
                .is(FlightType.Internal));

        return this.mongoTemplate.count(domestic, FlightInformation.class);
    }

    public List<FlightInformation> findByDeparture(String departure) {
        Query byDeparture = Query.query(Criteria.where("departure").is(departure));

        return this.mongoTemplate.find(byDeparture, FlightInformation.class);
    }

    public List<FlightInformation> findByDurationBetween(int minDuration,
                                                         int maxDuration) {
        Query byDuration = Query.query(
                Criteria.where("durationMin").gte(minDuration).lte(maxDuration)
        ).with(Sort.by(Sort.Direction.DESC, "durationMin"));

        return this.mongoTemplate.find(byDuration, FlightInformation.class);
    }

    public List<FlightInformation> delayedAtDeparture(String departure) {
        Query query = Query
                .query(Criteria.where("isDelayed").is(true)
                .and("departure").is(departure));

        return this.mongoTemplate.find(query, FlightInformation.class);
    }

    public List<FlightInformation> findByCityNotDelayed(String city) {
        Query query = Query
                .query(new Criteria()
                    .orOperator(
                            Criteria.where("departure").is(city),
                            Criteria.where("destination").is(city))
                    .andOperator(
                            Criteria.where("delayed").is(false)
                    )
                );

        return this.mongoTemplate.find(query, FlightInformation.class);
    }

    public List<FlightInformation> findByAircraft(String aircraft) {
        Query query = Query
                .query(Criteria.where("aircraft.model").is(aircraft));

        return this.mongoTemplate.find(query, FlightInformation.class);
    }

    public List<FlightInformation> findByFreeText(String text) {
        TextCriteria textCriteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);

        Query byFreeText = TextQuery.queryText(textCriteria)
                .sortByScore()
                .with(PageRequest.of(0, 3));

        return this.mongoTemplate.find(byFreeText, FlightInformation.class);
    }
}
