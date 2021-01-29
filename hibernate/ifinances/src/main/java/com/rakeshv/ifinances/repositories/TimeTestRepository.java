package com.rakeshv.ifinances.repositories;

import com.rakeshv.ifinances.models.TimeTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTestRepository extends JpaRepository<TimeTest, Long> {
}
