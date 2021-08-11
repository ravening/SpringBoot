package com.rakeshv.springbootneo4j.services;

import com.rakeshv.springbootneo4j.models.Person;
import com.rakeshv.springbootneo4j.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class PersonService {
    @Autowired
    PersonRepository personRepository;

//    public Collection<Person> getAllUsers() {
//        return personRepository.getAllUsers();
//    }
}
