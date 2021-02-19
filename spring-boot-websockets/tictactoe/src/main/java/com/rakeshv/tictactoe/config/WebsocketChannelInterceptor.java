package com.rakeshv.tictactoe.config;

import com.rakeshv.tictactoe.models.Response;
import com.rakeshv.tictactoe.services.GameStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Set;

@Slf4j
public class WebsocketChannelInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        Set<String> keys = headers.keySet();

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);
            if (multiValueMap != null) {
                String username = multiValueMap.toSingleValueMap().get("username");
                Object sessionId = headers.get("simpSessionId");
                log.info("Session id is {}, username is {}", sessionId, username);
            }
        }
        return message;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        return null;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    }
}
