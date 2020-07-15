package com.rakeshv.reactiveelasticsearch.controllers;

import com.rakeshv.reactiveelasticsearch.models.Employee;
import com.rakeshv.reactiveelasticsearch.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @GetMapping
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/name")
    public Flux<Employee> findByName(@PathVariable("name") String name) {
        return employeeRepository.findByName(name);
    }
}
