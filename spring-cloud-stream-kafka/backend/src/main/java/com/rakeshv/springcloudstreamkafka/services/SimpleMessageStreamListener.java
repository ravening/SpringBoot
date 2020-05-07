package com.rakeshv.springcloudstreamkafka.services;

import com.rakeshv.springcloudstreamkafka.models.ChatMessage;
import com.rakeshv.springcloudstreamkafka.streams.SimpleMessageStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class SimpleMessageStreamListener {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/topic/message";

    public SimpleMessageStreamListener(SimpMessagingTemplate template) {
        this.simpMessagingTemplate = template;
    }

    @StreamListener(SimpleMessageStream.INPUT)
    public void handleMessage(@Payload String message) {
        log.info("Received message {}", message);
        ChatMessage chatMessage = ChatMessage.builder()
                .contents(message)
                .date(new Date()).build();

        simpMessagingTemplate.convertAndSend(WS_MESSAGE_TRANSFER_DESTINATION, chatMessage);
    }
}
