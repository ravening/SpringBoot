package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketTypeJpaRepository extends JpaRepository<TicketType, String> {
    List<TicketType> findByIncludesWorkshopTrue();

    List<TicketType> findByIncludesWorkshopFalse();
}
