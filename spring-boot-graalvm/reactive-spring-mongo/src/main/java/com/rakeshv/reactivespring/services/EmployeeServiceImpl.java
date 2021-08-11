package com.rakeshv.reactivespring.services;

import com.rakeshv.reactivespring.models.Employee;
import com.rakeshv.reactivespring.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PreDestroy;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Flux<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Flux<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartmentContainsIgnoreCase(department);
    }

    @Override
    public Mono<Employee> saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Flux<Employee> saveAll(Flux<Employee> employeeFlux) {
        return employeeRepository.saveAll(employeeFlux);
    }

    @Override
    public Flux<Employee> getEmployeeStreamByDepartment(String deparment) {
        return employeeRepository.findWithTailableCursorByDepartment(deparment);
    }

    @PreDestroy
    public void cleanup() {
        employeeRepository.deleteAll();
    }
}
