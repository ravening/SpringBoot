package com.rakeshv.springbootkafka.controllers;

import com.rakeshv.springbootkafka.models.User;
import com.rakeshv.springbootkafka.services.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class UserController {
    @Autowired
    Producer producer;

    @GetMapping
    public void sendMessage() {
        User user = User.builder()
                .name("Donald Tp")
                .age(10)
                .nationality("Martian").build();

        producer.sendMessage(user.toString());
    }
}
