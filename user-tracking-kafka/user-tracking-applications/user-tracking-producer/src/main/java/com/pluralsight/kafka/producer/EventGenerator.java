package com.pluralsight.kafka.producer;

import com.github.javafaker.Faker;
import com.pluralsight.kafka.producer.enums.Color;
import com.pluralsight.kafka.producer.enums.ProductType;
import com.pluralsight.kafka.producer.enums.DesignType;
import com.pluralsight.kafka.producer.enums.UserId;
import com.pluralsight.kafka.producer.model.Event;
import com.pluralsight.kafka.producer.model.InternalProduct;
import com.pluralsight.kafka.producer.model.InternalUser;


public class EventGenerator {

    private Faker faker = new Faker();

    public Event generateEvent() {
        return Event.builder()
                .internalUser(generateRandomUser())
                .internalProduct(generateRandomObject())
                .build();
    }

    private InternalUser generateRandomUser() {
        return InternalUser.builder()
                .userId(faker.options().option(UserId.class))
                .username(faker.name().lastName())
                .dateOfBirth(faker.date().birthday())
                .build();
    }

    private InternalProduct generateRandomObject() {
        return InternalProduct.builder()
                .color(faker.options().option(Color.class))
                .type(faker.options().option(ProductType.class))
                .designType(faker.options().option(DesignType.class))
                .build();
    }
}
