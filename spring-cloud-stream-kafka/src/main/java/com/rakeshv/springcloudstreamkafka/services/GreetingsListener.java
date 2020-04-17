package com.rakeshv.springcloudstreamkafka.services;

import com.rakeshv.springcloudstreamkafka.ChatMessage;
import com.rakeshv.springcloudstreamkafka.streams.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GreetingsListener {
    private List<String> messages;

    @PostConstruct
    public void postConstruct() {
        messages = new ArrayList<>();
    }

    @StreamListener(GreetingsStreams.INPUT)
    public void handleMessage(@Payload ChatMessage message) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)
                .withZone(ZoneId.systemDefault());
        final String time = dtf.format(Instant.ofEpochMilli(message.getTime()));
        log.info("Received message at {} : Message is {}", time, message);
        messages.add(message.toString());
    }

    public List<String> getMessages() {
        log.info("Fetching list of messages received so far");
        return messages;
    }

    public void clearMessage() {
        log.info("Clearing all received messages");
        messages.clear();
    }
}
