package com.rakeshv.springcloudstreamkafka.services;

import com.rakeshv.springcloudstreamkafka.streams.SimpleMessageStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleMessageService {
    private final SimpleMessageStream simpleMessageStream;

    public SimpleMessageService(SimpleMessageStream stream) {
        this.simpleMessageStream = stream;
    }

    public void sendPlainTextMessage(String message) {
        log.info("Sending the message {}", message);
        MessageChannel messageChannel = simpleMessageStream.outboundMessage();
        messageChannel.send(MessageBuilder
                .withPayload(message).build());
    }
}
