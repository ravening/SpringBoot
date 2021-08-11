package com.rakeshv.springbootneo4j.controllers;

import com.rakeshv.springbootneo4j.models.Person;
import com.rakeshv.springbootneo4j.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/api/neo4j/user")
public class UserController {
    @Autowired
    PersonService personService;

//    @GetMapping("/listall")
//    public Collection<Person> getAllUsers() {
//        return personService.getAllUsers();
//    }
}
