package com.rakeshv.springdatamongodb.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FlightSenderVerticle extends AbstractVerticle {
    @Autowired
    Environment environment;

    private Router router;

    public FlightSenderVerticle(Router r) {
        this.router = r;
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start();

        this.router.get("/vertx/flights")
                .handler(this::getAllFlights);

//        vertx.createHttpServer()
//                .requestHandler(this.router)
//                .listen(8080);
    }

    private void sendMessage(String messageType, String message, RoutingContext routingContext) {
        vertx.eventBus()
                .<String>send(messageType, message, result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(result.result().body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end(result.result().body());
                    }
                });
    }

    private void getAllFlights(RoutingContext routingContext) {
        log.info("Sending request to search all flights");
        sendMessage(FlightReceiverVerticle.GET_FLIGHTS, "", routingContext);
    }
}
