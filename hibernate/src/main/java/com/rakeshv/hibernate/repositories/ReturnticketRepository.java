package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.ReturnTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnticketRepository extends JpaRepository<ReturnTicket, Long> {
}
