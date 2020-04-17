package com.rakeshv.springcloudstreamkafka.controllers;

import com.rakeshv.springcloudstreamkafka.ChatMessage;
import com.rakeshv.springcloudstreamkafka.services.GreetingsListener;
import com.rakeshv.springcloudstreamkafka.services.GreetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class MessageController {
    private GreetingsService greetingsService;

    @Autowired
    GreetingsListener greetingsListener;

    public MessageController(GreetingsService service) {
        this.greetingsService = service;
    }

    @GetMapping("/sendmessage")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestParam("message") String message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .contents(message)
                .time(System.currentTimeMillis()).build();

        greetingsService.sendGreeting(chatMessage);
    }

    @GetMapping("/send/{message}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void send(@PathVariable("message") String message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .contents(message)
                .time(System.currentTimeMillis()).build();

        greetingsService.sendGreeting(chatMessage);
    }

    @GetMapping("/readmessage")
    public ResponseEntity<List<String>> getMessage() {
        return new ResponseEntity<>(greetingsListener.getMessages(), HttpStatus.OK);
    }

    @GetMapping("/clearmessages")
    public String clearMessages() {
        greetingsListener.clearMessage();
        return "All messages cleared";
    }
}

