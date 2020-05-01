package com.rakeshv.websocket.controllers;

import com.rakeshv.websocket.models.User;
import com.rakeshv.websocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @MessageMapping("/hello")
    public void greeting(User user) {
        user.setName("New User : " + user.getName() + " : Saved at " + new Date());
        userService.saveUser(user);
    }

    @MessageMapping("/listall")
    public void listAllUsers() {
        userService.publishAllUsers();
    }

    @GetMapping("/api/saveuser/{name}")
    public void saveUser(@PathVariable("name") String name) {
        User user = User.builder()
                .name("New user : " + name + " : Saved at " + new Date()).build();

        userService.saveUser(user);
    }

    @PostMapping("/api/user")
    public void saveUser(@RequestBody User user) {
        user.setName("New user : " + user.getName() + " : Saved at " + new Date());
        userService.saveUser(user);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
