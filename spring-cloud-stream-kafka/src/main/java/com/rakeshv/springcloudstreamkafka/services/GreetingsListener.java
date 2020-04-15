package com.rakeshv.springcloudstreamkafka.services;

import com.rakeshv.springcloudstreamkafka.ChatMessage;
import com.rakeshv.springcloudstreamkafka.streams.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Component
@Slf4j
public class GreetingsListener {
    @StreamListener(GreetingsStreams.INPUT)
    public void handleMessage(@Payload ChatMessage message) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)
                .withZone(ZoneId.systemDefault());
        final String time = dtf.format(Instant.ofEpochMilli(message.getTime()));
        log.info("Received message at {} : Message is {}", time, message);
    }
}
