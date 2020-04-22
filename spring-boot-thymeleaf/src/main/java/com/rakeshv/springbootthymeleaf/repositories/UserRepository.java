package com.rakeshv.springbootthymeleaf.repositories;

import com.rakeshv.springbootthymeleaf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContains(String name);

    List<User> findByEmailContains(String email);

    List<User> findByAgeGreaterThanAndAgeLessThan(int min, int max);
}
