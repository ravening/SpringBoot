package com.rakeshv.springbootvuejs.controllers;

import com.rakeshv.springbootvuejs.models.Course;
import com.rakeshv.springbootvuejs.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/courses/{username}")
    public List<Course> findCourseByUsername(@PathVariable("username") String username) {
        return courseRepository.findByUsernameContains(username);
    }
}
