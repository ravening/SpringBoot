package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.inheritance.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
