package com.rakeshv.springbootactuator.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ProjectInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("project_name", "spring boot actuator")
                .withDetail("owned by", "Rakesh Venkatesh")
                .withDetail("extras", "Spring security");
    }
}
