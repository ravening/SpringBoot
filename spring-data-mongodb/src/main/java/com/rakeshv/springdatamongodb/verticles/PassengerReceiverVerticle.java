package com.rakeshv.springdatamongodb.verticles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakeshv.springdatamongodb.domains.Passenger;
import com.rakeshv.springdatamongodb.services.PassengerQueryService;
import com.rakeshv.springdatamongodb.services.PassengerService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@Slf4j
public class PassengerReceiverVerticle extends AbstractVerticle {
    public static final String GET_ALL_PASSENGERS = "get.all.passengers";
    public static final String GET_ADULT_PASSENGERS = "get.adult.passengers";
    public static final String GET_MINOR_PASSENGERS = "get.minor.passengers";
    public static final String GET_SENIOR_PASSENGERS = "get.senior.passengers";

    private final ObjectMapper mapper = Json.mapper;

    @Autowired
    PassengerQueryService passengerQueryService;
    @Autowired
    PassengerService passengerService;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        log.info("Received message to get all passengers");
        super.start();
        vertx.eventBus()
                .<String>consumer(GET_ALL_PASSENGERS)
                .handler(getAll(passengerQueryService));

        vertx.eventBus()
                .<String>consumer(GET_ADULT_PASSENGERS)
                .handler(getAdultPassengers());

        vertx.eventBus()
                .<String>consumer(GET_MINOR_PASSENGERS)
                .handler(getMinorPassengers());

        vertx.eventBus()
                .<String>consumer(GET_SENIOR_PASSENGERS)
                .handler(getSeniorPassengers());
    }

    public Handler<Message<String>> getAll(PassengerQueryService passengerQueryService) {
        log.info("in function getall");
        return message -> vertx.<String>executeBlocking(future -> {
            try {
                List<Passenger> passengers = passengerQueryService.getAll();
                log.info("Fetching all passengers");
                log.info("passengers are {}", passengers);
                future.complete(mapper.writeValueAsString(passengers));
            } catch (Exception e) {
                log.error("Exception : {}", e.getMessage());
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                message.reply(result.result());
            } else {
                message.reply(result.cause().toString());
            }
        });
    }

    public Handler<Message<String>> getAdultPassengers() {
        return message -> vertx.<String>executeBlocking(future -> {
            try {
                Flux<Passenger> passengerFlux = passengerService.getAdultPassengers();
                List<Passenger> passengers = passengerFlux.collectList().block();
                log.info("Fetching all adult passengers");
                log.info("passengers are {}", passengers);
                future.complete(mapper.writeValueAsString(passengers));
            } catch (Exception e) {
                log.error("Exception : {}", e.getMessage());
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                message.reply(result.result());
            } else {
                message.reply(result.cause().toString());
            }
        });
    }

    public Handler<Message<String>> getMinorPassengers() {
        return message -> vertx.<String>executeBlocking(future -> {
            try {
                Flux<Passenger> passengerFlux = passengerService.getMinorPassengers();
                List<Passenger> passengers = passengerFlux.collectList().block();
                log.info("Fetching all minor passengers");
                log.info("passengers are {}", passengers);
                future.complete(mapper.writeValueAsString(passengers));
            } catch (Exception e) {
                log.error("Exception : {}", e.getMessage());
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                message.reply(result.result());
            } else {
                message.reply(result.cause().toString());
            }
        });
    }

    public Handler<Message<String>> getSeniorPassengers() {
        log.info("in function getall");
        return message -> vertx.<String>executeBlocking(future -> {
            try {
                Flux<Passenger> passengerFlux = passengerService.getSeniorPassengers();
                List<Passenger> passengers = passengerFlux.collectList().block();
                log.info("Fetching all passengers");
                log.info("passengers are {}", passengers);
                future.complete(mapper.writeValueAsString(passengers));
            } catch (Exception e) {
                log.error("Exception : {}", e.getMessage());
                future.fail(e);
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
