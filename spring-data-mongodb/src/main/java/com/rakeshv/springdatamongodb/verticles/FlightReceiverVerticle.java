package com.rakeshv.springdatamongodb.verticles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakeshv.springdatamongodb.domains.FlightInformation;
import com.rakeshv.springdatamongodb.services.FlightInformationService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FlightReceiverVerticle extends AbstractVerticle {
    public static final String GET_FLIGHTS = "get.flights";
    private final ObjectMapper mapper = Json.mapper;

    @Autowired
    FlightInformationService flightInformationService;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start();
        vertx.eventBus()
                .<String>consumer(GET_FLIGHTS)
                .handler(getAllFlights());
    }

    private Handler<Message<String>> getAllFlights() {
        return message -> vertx.<String>executeBlocking(future -> {
            try {
                log.info("searching for flights in the database");
                Flux<FlightInformation> flightInformations =
                        flightInformationService.getAllFlightInformation();
                List<FlightInformation> flightInformationList = new ArrayList<>();
                flightInformations.subscribe(flightInformation -> {
                    log.info("Flight is {}", flightInformation);
                    flightInformationList.add(flightInformation);
                });

                future.tryComplete(mapper.writeValueAsString(flightInformationList));
            } catch (Exception e) {
                log.error("Exception : {}", e.getMessage());
            }
        }, result -> {
            if (result.succeeded()) {
                message.reply(result.result());
            } else {
                message.reply(result.cause().toString());
            }
        });
    }
}
