package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdRepository extends JpaRepository<CD, Long> {
}
