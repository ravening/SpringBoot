package com.rakeshv.springbootvuejs.services;

import com.rakeshv.springbootvuejs.models.Course;
import com.rakeshv.springbootvuejs.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCourseByUsername(String username) {
        return courseRepository.findByUsernameContains(username);
    }

    public Course getById(long id) {
        log.info("Fetching the course with id {}", id);
        return courseRepository.findById(id).orElse(null);
    }

    public Course deleteCourse(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        courseOptional.ifPresent(course -> {
            courseRepository.delete(course);
            log.info("Successfully delete the course : {}", course);
        });

        return courseOptional.orElse(null);
    }

    public void saveCourse(Course course) {
        log.info("Saving the course {}", course);
        courseRepository.save(course);
    }

    public Course editCourse(Long id, Course course) {
        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()) {
            Course existingCourse = optionalCourse.get();
            existingCourse.setDescription(course.getDescription());
            existingCourse.setUsername(course.getUsername());
            courseRepository.save(existingCourse);
            return existingCourse;
        } else {
            return null;
        }

    }
}
