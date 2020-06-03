package com.rakeshv.libraryapp.repositories;

import com.rakeshv.libraryapp.entities.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryEntityRepository extends JpaRepository<LibraryEntity, Integer> {
}
