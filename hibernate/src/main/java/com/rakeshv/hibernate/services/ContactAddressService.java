package com.rakeshv.hibernate.services;

import com.rakeshv.hibernate.models.ContactAddress;
import com.rakeshv.hibernate.repositories.ContactAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactAddressService {
    private final ContactAddressRepository contactAddressRepository;

    public void saveContactAddress(ContactAddress contactAddress) {
        this.contactAddressRepository.save(contactAddress);
    }
}
