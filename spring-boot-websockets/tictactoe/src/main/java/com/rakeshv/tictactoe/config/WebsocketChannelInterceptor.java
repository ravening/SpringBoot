package com.rakeshv.tictactoe.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Set;

public class WebsocketChannelInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);
//        Set<String> keys = headers.keySet();
//        keys.forEach(k -> System.out.println("eky is " + k));
//        headers.values().forEach(x -> System.out.println("value is " + x));
//        multiValueMap.values().forEach(System.out::println);

        Set<Map.Entry<String, Object>> entrySet = headers.entrySet();
//        for (Map.Entry e : entrySet) {
//            System.out.println("key is " + e.getKey() + " and value is " + e.getValue());
//        }
        return message;
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        Set<Map.Entry<String, Object>> entrySet = headers.entrySet();
        for (Map.Entry e : entrySet) {
//            System.out.println("key is " + e.getKey() + " and value is " + e.getValue());
        }
        return null;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
//        MessageHeaders headers = message.getHeaders();
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//
//        Set<Map.Entry<String, Object>> entrySet = headers.entrySet();
//        for (Map.Entry e : entrySet) {
//            System.out.println("key is " + e.getKey() + " and value is " + e.getValue());
//        }
    }
}
