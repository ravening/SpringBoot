package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {
}
