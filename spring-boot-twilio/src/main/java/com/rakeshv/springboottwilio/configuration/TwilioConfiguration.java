package com.rakeshv.springboottwilio.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TwilioConfiguration {

    private String accountSid;
    private String authToken;
    private String trialNumber;
}
