package com.rakeshv.websocket.service;

import com.rakeshv.websocket.models.User;
import com.rakeshv.websocket.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class UserService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/topic/hi";

    @Autowired
    UserRepository userRepository;

    AtomicInteger userCount = new AtomicInteger(0);

    public void saveUser(User user) {
        userRepository.save(user);
        userCount.getAndIncrement();
        publishUser(user);
    }

    public void publishUser(User user) {
        simpMessagingTemplate.convertAndSend(WS_MESSAGE_TRANSFER_DESTINATION, user);
    }

    public UserService(SimpMessagingTemplate template) {
        this.simpMessagingTemplate = template;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void publishAllUsers() {
        for (User user : getAllUsers()) {
            simpMessagingTemplate.convertAndSend(WS_MESSAGE_TRANSFER_DESTINATION, user);
        }
    }
}
