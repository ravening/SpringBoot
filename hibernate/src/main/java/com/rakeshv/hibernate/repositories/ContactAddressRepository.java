package com.rakeshv.hibernate.repositories;

import com.rakeshv.hibernate.models.ContactAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactAddressRepository extends JpaRepository<ContactAddress, Long> {
}
