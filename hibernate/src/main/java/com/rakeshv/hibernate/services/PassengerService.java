package com.rakeshv.hibernate.services;

import com.rakeshv.hibernate.models.Passengers;
import com.rakeshv.hibernate.repositories.PassengersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {
    private PassengersRepository passengersRepository;

    public List<Passengers> getAllPassengers() {
        return this.passengersRepository.findAll();
    }

    public Optional<Passengers> getPassengerById(Long id) {
        return this.passengersRepository.findById(id);
    }
}
