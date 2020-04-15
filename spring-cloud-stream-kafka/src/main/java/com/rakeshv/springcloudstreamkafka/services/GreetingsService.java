package com.rakeshv.springcloudstreamkafka.services;

import com.rakeshv.springcloudstreamkafka.ChatMessage;
import com.rakeshv.springcloudstreamkafka.streams.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@Slf4j
public class GreetingsService {
    private final GreetingsStreams greetingsStreams;

    public GreetingsService(GreetingsStreams streams) {
        this.greetingsStreams = streams;
    }

    public void sendGreeting(ChatMessage message) {
        log.info("Sending the chat message : {}", message);
        MessageChannel messageChannel = greetingsStreams.outboundGreetings();
        messageChannel.send(MessageBuilder
                .withPayload(message)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
