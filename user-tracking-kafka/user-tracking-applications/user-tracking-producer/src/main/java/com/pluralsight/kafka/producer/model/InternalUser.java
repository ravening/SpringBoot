package com.pluralsight.kafka.producer.model;

import com.pluralsight.kafka.producer.enums.UserId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class InternalUser {

    private UserId userId;

    private String username;

    private Date dateOfBirth;

}
