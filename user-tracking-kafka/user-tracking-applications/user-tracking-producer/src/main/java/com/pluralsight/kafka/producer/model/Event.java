package com.pluralsight.kafka.producer.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Event {

    private InternalUser internalUser;

    private InternalProduct internalProduct;

}
