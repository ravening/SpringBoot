package com.rakeshv.springdatamongodb.verticles;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MainVerticle {
    @Autowired
    RouterConfig routerConfig;
    @Autowired
    PassengerReceiverVerticle passengerReceiverVerticle;
    @Autowired
    FlightReceiverVerticle flightReceiverVerticle;

    @Bean
    public void createVerticles() {
        Vertx vertx = Vertx.vertx();
        Router router = routerConfig.getRouter();
        vertx.deployVerticle(new FlightSenderVerticle(router));
        vertx.deployVerticle(new PassengerSenderVerticle(router));
        vertx.deployVerticle(passengerReceiverVerticle);
        vertx.deployVerticle(flightReceiverVerticle);
    }


}
