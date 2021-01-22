package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketpriceJpaRepository extends JpaRepository<TicketPrice, Long> {
}
