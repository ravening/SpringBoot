package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.EmbeddableTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmbeddableTicketRepository extends JpaRepository<EmbeddableTicket, Long> {
}
