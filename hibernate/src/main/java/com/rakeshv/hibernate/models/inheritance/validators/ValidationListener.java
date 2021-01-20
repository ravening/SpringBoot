package com.rakeshv.hibernate.models.inheritance.validators;

import com.rakeshv.hibernate.models.inheritance.Author;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class ValidationListener {

    @PrePersist
    @PreUpdate
    private void validate(Author author) {
        if (author.getFirstName() == null || "".equalsIgnoreCase(author.getFirstName()))
            throw new IllegalArgumentException("Invalid first name");
        if (author.getLastName() == null || "".equalsIgnoreCase(author.getLastName()))
            throw new IllegalArgumentException("Invalid last name");
    }
}
