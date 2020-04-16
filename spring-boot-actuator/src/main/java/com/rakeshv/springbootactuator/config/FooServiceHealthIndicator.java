package com.rakeshv.springbootactuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class FooServiceHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up()
                .withDetail("project", "custom health indicator")
                .withDetail("owner", "Rakesh Venkatesh").build();
    }
}
