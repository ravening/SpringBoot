package com.rakeshv.springbootvuejs.controllers;

import com.rakeshv.springbootvuejs.models.Course;
import com.rakeshv.springbootvuejs.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class CourseController {
    @Autowired
    CourseService courseService;
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        log.info("Fetching all the courses");
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{username}")
    public List<Course> findCourseByUsername(@PathVariable("username") String username) {
        log.info("Fetching courses for username {}", username);
        return courseService.getCourseByUsername(username);
    }

    @GetMapping("/courses/id/{id}")
    public Course getCourseById(@PathVariable("id") long id) {
        return courseService.getById(id);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable("id") Long id) {
        Course course = courseService.deleteCourse(id);

        if (course != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") Long id,
                                               @RequestBody Course course) {
        Course updatedCourse = courseService.editCourse(id, course);

        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<Void> createCourse(@RequestBody Course course) {
        courseService.saveCourse(course);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(course.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }
}
