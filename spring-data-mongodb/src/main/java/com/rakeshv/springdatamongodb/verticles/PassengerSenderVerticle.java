package com.rakeshv.springdatamongodb.verticles;

import com.rakeshv.springdatamongodb.services.PassengerQueryService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PassengerSenderVerticle extends AbstractVerticle {
    @Autowired
    Environment environment;

    private Router router;

    public PassengerSenderVerticle(Router r) {
        this.router = r;
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start();

        this.router.get("/vertx/passengers")
                .handler(this::getAllPassengers);
        router.get("/vertx/passengers/adults")
                .handler(this::getAdultPassengers);
        router.get("/vertx/passengers/minors")
                .handler(this::getMinorPassengers);
        router.get("/vertx/passengers/seniors")
                .handler(this::getSeniorPassengers);

        this.router.route("/").handler(handler -> {
            HttpServerResponse response = handler.response();
            response.putHeader("content-type", "text/html")
                    .end("<pre>" +
                            "<h1>Hello from the Spring Boot vertx web application</h1></pre>" +
                            "<h2>Please navigate to /vertx/query/adults|minors|serniors</h2>");
        });

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
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

    private void getAllPassengers(RoutingContext routingContext) {
        log.info("Sending request to fetch all passengers");
        sendMessage(PassengerReceiverVerticle.GET_ALL_PASSENGERS, "", routingContext);
    }

    private void getAdultPassengers(RoutingContext routingContext) {
        log.info("Fetching all the adult passengers");
        sendMessage(PassengerReceiverVerticle.GET_ADULT_PASSENGERS, "", routingContext);
    }

    private void getMinorPassengers(RoutingContext routingContext) {
        log.info("Fetching all the adult passengers");
        sendMessage(PassengerReceiverVerticle.GET_MINOR_PASSENGERS, "", routingContext);
    }

    private void getSeniorPassengers(RoutingContext routingContext) {
        log.info("Fetching all the adult passengers");
        sendMessage(PassengerReceiverVerticle.GET_SENIOR_PASSENGERS, "", routingContext);
    }
}
