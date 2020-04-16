//package com.rakeshv.springdatamongodb.utils;
//
//import com.rakeshv.springdatamongodb.domains.Airport;
//import com.rakeshv.springdatamongodb.domains.FlightInformation;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FlightCascadeEventListener extends AbstractMongoEventListener<Object> {
//    private MongoTemplate mongoTemplate;
//
//    public FlightCascadeEventListener(MongoTemplate template) {
//        this.mongoTemplate = template;
//    }
//
//    // Better to use generic cascade listener
//    // Refer to file GenericCascadeListener
//    // If you explicityly want to use FlightInformation, then uncomment it
//    @Override
//    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
//        Object doc = event.getSource();
//
//        if ((doc instanceof FlightInformation) &&
//                (((FlightInformation) doc).getDeparture() != null)) {
//            Airport departure = ((FlightInformation) doc).getDeparture();
//            mongoTemplate.save(departure);
//        }
//
//        if ((doc instanceof FlightInformation) &&
//                (((FlightInformation) doc).getDestination() != null)) {
//            Airport destination = ((FlightInformation) doc).getDestination();
//            mongoTemplate.save(destination);
//        }
//    }
//}
