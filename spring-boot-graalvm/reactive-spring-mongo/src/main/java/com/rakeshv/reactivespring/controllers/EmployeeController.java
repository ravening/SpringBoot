package com.rakeshv.reactivespring.controllers;

import com.rakeshv.reactivespring.models.Employee;
import com.rakeshv.reactivespring.models.EmployeeEvent;
import com.rakeshv.reactivespring.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping
    public Flux<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable("id") final String id) {
        log.info("Fetching employee by id {}", id);
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEvents(@PathVariable("id") final String id) {
        return employeeService.getEmployeeById(id)
                .flatMapMany(employee -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
                    Flux<EmployeeEvent> employeeEventFlux =
                            Flux.fromStream(
                                    Stream.generate(() -> EmployeeEvent.builder()
                                            .employee(employee)
                                            .date(new Date()).build())
                            );

                    return Flux.zip(interval, employeeEventFlux)
                            .map(Tuple2::getT2);
                });
    }

    @GetMapping("/department/{name}")
    public Flux<Employee> getEmployeesByDeparment(@PathVariable("name") String name) {
        return employeeService.getEmployeesByDepartment(name);
    }

    @GetMapping("/name/{name}")
    public Flux<Employee> getEmployeesByName(@PathVariable("name") String name) {
        return employeeService.getEmployeeByName(name);
    }

    @GetMapping("/save")
    public void dummyFunction() {
        Random random = new Random();
        int number = random.nextInt(1000);
        Employee employee = Employee.builder()
                .name("test-" + number)
                .department("cloud").build();

        employeeService.saveEmployee(employee).subscribe();
    }

    // Execute this command in mongodb for this to work
    // db.createCollection("messages", { capped: true, size: 5000000, max: 10000 });
    @GetMapping(value = "/department/stream/{name}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> streamDepartment(@PathVariable("name") String name) {
        return employeeService.getEmployeeStreamByDepartment(name);
    }
}
