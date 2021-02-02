package com.rakeshv.ifinances.repositories;

import com.rakeshv.ifinances.models.Bond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BondRepository extends JpaRepository<Bond, Long> {
}
