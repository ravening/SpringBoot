package com.rakeshv.springcloudstreamkafka.controllers;

import com.rakeshv.springcloudstreamkafka.ChatMessage;
import com.rakeshv.springcloudstreamkafka.services.GreetingsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class MessageController {
    private GreetingsService greetingsService;

    public MessageController(GreetingsService service) {
        this.greetingsService = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestParam("message") String message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .contents(message)
                .time(System.currentTimeMillis()).build();

        greetingsService.sendGreeting(chatMessage);
    }
}

