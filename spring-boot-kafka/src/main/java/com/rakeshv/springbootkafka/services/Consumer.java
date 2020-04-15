package com.rakeshv.springbootkafka.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class Consumer {
    private static final String TOPIC = "users";

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(ConsumerRecord record) throws IOException {
        log.info("Received message {} on topic {}", record.value(), record.topic());
        log.info("Entire message is {}", record.toString());
    }
}
