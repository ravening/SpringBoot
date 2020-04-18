package com.rakeshv.springbootvuejs.utils;

import com.rakeshv.springbootvuejs.models.Course;
import com.rakeshv.springbootvuejs.models.Todo;
import com.rakeshv.springbootvuejs.repositories.CourseRepository;
import com.rakeshv.springbootvuejs.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class DbSeeder implements CommandLineRunner {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Buy milk", "Eat pizza", "Write tutorial", "Study Vue.js", "Go kayaking").forEach(name -> {
            Todo todo = Todo.builder()
                    .title(name).build();
            todoRepository.save(todo);
        });
//        todoRepository.findAll().forEach(System.out::println);

        Course course = Course.builder()
                .description("Spring boot and Angular")
                .username("rvenkatesh").build();

        Course course1 = Course.builder()
                .description("Spring cloud stream apache kafka")
                .username("rakesh").build();

        Course course2 = Course.builder()
                .description("Vuejs with Spring boot")
                .username("rakgenius").build();

        Course course3 = Course.builder()
                .description("Microservices with spring cloud")
                .username("rakesh").build();

        List<Course> courses = Arrays.asList(
                course,
                course1,
                course2,
                course3
        );

        courseRepository.saveAll(courses);
        courseRepository.findAll().forEach(System.out::println);
    }
}
