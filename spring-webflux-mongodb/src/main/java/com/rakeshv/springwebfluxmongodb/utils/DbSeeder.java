package com.rakeshv.springwebfluxmongodb.utils;

import com.rakeshv.springwebfluxmongodb.models.Employee;
import com.rakeshv.springwebfluxmongodb.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DbSeeder implements CommandLineRunner {
    @Autowired
    EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {
        Employee employee = Employee.builder()
                .name("Rakesh")
                .department("cloud")
                .salary(1111L).build();

        Employee employee1 = Employee.builder()
                .name("Jeff")
                .department("wolk")
                .salary(2222L).build();

        Employee employee2 = Employee.builder()
                .name("Tom")
                .department("Dev")
                .salary(3333L).build();

        Employee employee3 = Employee.builder()
                .name("Bob")
                .department("qa")
                .salary(4444L).build();

        System.out.println("====saving the employees to db====");
//        Stream.of(employee,
//                  employee1,
//                employee2,
//                employee3)
//                .forEach(emp -> {
//                    employeeService.saveEmployee(emp)
//                            .subscribe(System.out::println);
//                });

        employeeService.saveAll(
                Flux.just(employee, employee1, employee2, employee3)
        ).subscribe(System.out::println);

    }
}
