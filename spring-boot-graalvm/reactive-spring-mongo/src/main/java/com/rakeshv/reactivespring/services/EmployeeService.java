package com.rakeshv.reactivespring.services;

import com.rakeshv.reactivespring.models.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    public Flux<Employee> getAllEmployees();

    public Flux<Employee> getEmployeeByName(String name);

    public Flux<Employee> getEmployeesByDepartment(String department);

    public Mono<Employee> saveEmployee(Employee employee);

    public Mono<Employee> getEmployeeById(String id);

    public Flux<Employee> saveAll(Flux<Employee> employeeFlux);

    public Flux<Employee> getEmployeeStreamByDepartment(String deparment);
}
