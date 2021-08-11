package com.rakeshv.reactivespring.repositories;

import com.rakeshv.reactivespring.models.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Long> {
    Flux<Employee> findByNameContainsIgnoreCase(String name);

    Flux<Employee> findByDepartmentContainsIgnoreCase(String department);

    Mono<Employee> findById(String id);

    @Tailable
    Flux<Employee> findWithTailableCursorByDepartment(String department);
}
