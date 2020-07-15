package com.rakeshv.reactiveelasticsearch;

import com.rakeshv.reactiveelasticsearch.models.Department;
import com.rakeshv.reactiveelasticsearch.models.Employee;
import com.rakeshv.reactiveelasticsearch.models.Organization;
import com.rakeshv.reactiveelasticsearch.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class DatabaseSeeder {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ReactiveElasticsearchTemplate template;
    @Autowired
    ReactiveElasticsearchClient client;

    @EventListener
    public void storeDocuments(ApplicationReadyEvent event) {
        Mono<Boolean> exists = client.indices().existsIndex(request -> request.indices("sample"));
        exists.subscribe(ex -> {
            if (!ex) {
                log.info("Creating the index: sample");
                client.indices().createIndex(request -> request.index("sample"));
            }
        });

        List<Employee> employees = getEmployees();
        Flux<Employee> employeeFlux = employeeRepository.saveAll(employees);
        employeeFlux.subscribe(employee -> log.info("Saved employee: {}", employee),
                        error -> log.error("Unable to save employees: {}", error.getMessage()),
                () -> log.info("Completed"));
    }

    private List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Random r = new Random();
            Department department = Department.builder()
                    .name("Test-" + r.nextInt(100))
                    .id(r.nextLong()).build();
            Organization organization = Organization.builder()
                    .name("test-" + r.nextInt(100))
                    .address("road-" + r.nextLong())
                    .id(r.nextLong()).build();
            Employee employee = Employee.builder()
                    .name("Johnsmith-" + r.nextInt(10000))
                    .age(r.nextInt(100))
                    .position("developer-" + r.nextInt(10))
                    .department(department)
                    .organization(organization).build();
            employees.add(employee);
        }

        return employees;
    }
}
