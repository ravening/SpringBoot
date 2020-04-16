package com.rakeshv.springbootactuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MaxMemoryHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean invalid = Runtime.getRuntime().maxMemory() < (100 * 1024 * 1024);
        Status status = invalid ? Status.DOWN : Status.UP;
        return Health.status(status)
                .withDetail("max memory", Runtime.getRuntime().maxMemory())
                .withDetail("processors", Runtime.getRuntime().availableProcessors())
                .withDetail("total memory", Runtime.getRuntime().totalMemory())
                .withDetail("free memory", Runtime.getRuntime().freeMemory())
                .build();
    }
}
