package com.rakeshv.springbootgraalvm;

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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Stream;

@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class, proxyBeanMethods = false)
public class SpringBootGraalvmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraalvmApplication.class, args);
	}

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
class Customer {
	@Id @GeneratedValue
	private Long id;
	private String name;
}

@Repository
interface CustomerRepository extends JpaRepository<Customer, Long>{}

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class CustomerController {
	private final CustomerRepository customerRepository;

	@GetMapping("/customers")
	public Collection<Customer> getCustomers() {
		return this.customerRepository.findAll();
	}
}

@Component
@RequiredArgsConstructor
class Initializer implements ApplicationListener<ApplicationReadyEvent> {
	private final CustomerRepository customerRepository;
	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		Stream.of("John", "Doe", "Foo", "Bar")
				.map(name -> Customer.builder().name(name).build())
				.map(this.customerRepository::save)
				.forEach(System.out::println);
	}
}
