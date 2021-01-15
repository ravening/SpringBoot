package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengersRepository extends JpaRepository<Passengers, Long> {
}
