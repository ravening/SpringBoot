package com.rakeshv.springdatamongodb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class SpringDataMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataMongodbApplication.class, args);
    }

    @PostConstruct
    public void display() {
        log.error("========hello world=======");
    }
}
