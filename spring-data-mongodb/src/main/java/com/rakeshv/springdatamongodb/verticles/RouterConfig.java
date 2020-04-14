package com.rakeshv.springdatamongodb.verticles;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public Router getRouter() {
        return Router.router(Vertx.vertx());
    }
}
