package com.rakeshv.hibernate.models.inheritance.validators;

import com.rakeshv.hibernate.models.inheritance.Author;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.time.LocalDate;
import java.time.Period;

public class AgeListener {
    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateAge(Author author) {
        if (author.getDateOfBirth() == null) {
            author.setAge(null);
            return;
        }

        author.setAge(Period.between(author.getDateOfBirth(), LocalDate.now()).getYears());
        System.out.println("=======age is " + author.getAge());
    }
}
