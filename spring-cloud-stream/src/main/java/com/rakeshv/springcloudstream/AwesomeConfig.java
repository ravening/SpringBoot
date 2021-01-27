package com.rakeshv.springcloudstream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Configuration
public class AwesomeConfig {
    @Bean
    public Supplier<String> awesomeBean() {
        return () -> LocalDateTime.now().toString();
    }
}
