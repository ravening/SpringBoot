package com.rakeshv.networkoverview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class NetworkOverviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkOverviewApplication.class, args);
    }

}
