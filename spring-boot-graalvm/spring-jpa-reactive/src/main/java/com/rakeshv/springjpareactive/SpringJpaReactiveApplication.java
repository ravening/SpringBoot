package com.rakeshv.springjpareactive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication(
        exclude = SpringDataWebAutoConfiguration.class,
        proxyBeanMethods = false
)
public class SpringJpaReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaReactiveApplication.class, args);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Customer {
    @Id
    private Long id;
    private String name;
}

@Repository
interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {}

@Component
@RequiredArgsConstructor
class Initializer implements ApplicationListener<ApplicationReadyEvent> {
    private final CustomerRepository customerRepository;
    private final DatabaseClient databaseClient;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Flux<Customer> save = Flux.just("John", "Doe", "Foo", "Bar")
                .map(name -> Customer.builder().name(name).build())
                .flatMap(this.customerRepository::save);

        String sql = "Create table CUSTOMER(id serial primary key, name varchar(255))";
        this.databaseClient
                .execute(sql)
                .fetch()
                .rowsUpdated()
                .thenMany(save)
                .thenMany(this.customerRepository.findAll())
                .subscribe(System.out::println);
    }
}

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class CustomerController {
    private final CustomerRepository customerRepository;

    @GetMapping("/customers")
    public Flux<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }
}
