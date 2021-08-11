package com.rakeshv.springbootneo4j;

import com.rakeshv.springbootneo4j.models.Movie;
import com.rakeshv.springbootneo4j.models.Person;
import com.rakeshv.springbootneo4j.models.Role;
import com.rakeshv.springbootneo4j.repositories.MovieRepository;
import com.rakeshv.springbootneo4j.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        Movie italianJob = new Movie();
        italianJob.setTitle("The Italian Job");
        italianJob.setReleased(1999);
        movieRepository.save(italianJob);

        Person mark = new Person();
        mark.setName("Mark Wahlberg");
        personRepository.save(mark);

        Role charlie = new Role();
        charlie.setMovie(italianJob);
        charlie.setPerson(mark);
        List<String> roleNames = new ArrayList<>();
        roleNames.add("Charlie Croker");
        charlie.setRoles(roleNames);
        List<Role> roles = new ArrayList();
        roles.add(charlie);
        italianJob.setActors(roles);
        movieRepository.save(italianJob);
    }
}
