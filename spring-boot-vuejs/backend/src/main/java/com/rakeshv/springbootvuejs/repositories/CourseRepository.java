package com.rakeshv.springbootvuejs.repositories;

import com.rakeshv.springbootvuejs.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUsernameContains(String username);
}
