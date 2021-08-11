package com.rakeshv.springboottwilio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Builder
public class SmsRequest {

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String message;

    public SmsRequest(@JsonProperty("phoneNumber") String  phoneNumber,
                      @JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
