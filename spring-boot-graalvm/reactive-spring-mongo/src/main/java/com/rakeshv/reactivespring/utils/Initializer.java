package com.rakeshv.reactivespring.utils;

import com.rakeshv.reactivespring.models.Employee;
import com.rakeshv.reactivespring.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class Initializer implements ApplicationListener<ApplicationReadyEvent> {
    private final EmployeeService employeeService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Employee employee1 = Employee.builder()
                .name("Jeff")
                .department("hr")
                .salary(2222L).build();

        Employee employee2 = Employee.builder()
                .name("Tom")
                .department("Dev")
                .salary(3333L).build();

        Employee employee3 = Employee.builder()
                .name("Bob")
                .department("qa")
                .salary(4444L).build();

        employeeService.saveAll(
                Flux.just(employee1, employee2, employee3)
        ).subscribe(System.out::println);
    }
}
