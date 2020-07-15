package com.rakeshv.reactiveelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@SpringBootApplication
@EnableReactiveElasticsearchRepositories
public class ReactiveElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveElasticsearchApplication.class, args);
    }

}
