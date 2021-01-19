package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.OneWayTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnewayTicketRepository extends JpaRepository<OneWayTicket, Long> {
}
