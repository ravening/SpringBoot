package com.rakeshv.hibernate.utils;

import com.rakeshv.hibernate.models.Airport;
import com.rakeshv.hibernate.models.ContactAddress;
import com.rakeshv.hibernate.models.Passengers;
import com.rakeshv.hibernate.models.Tickets;
import com.rakeshv.hibernate.repositories.AirportRepository;
import com.rakeshv.hibernate.repositories.PassengersRepository;
import com.rakeshv.hibernate.repositories.TicketsRepository;
import com.rakeshv.hibernate.services.ContactAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbLoader implements CommandLineRunner {

    @Autowired
    AirportRepository airportRepository;
    @Autowired
    PassengersRepository passengersRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    ContactAddressService contactAddressService;

    @Override
    public void run(String... args) throws Exception {
        Airport airport = Airport.builder()
                .name("Test")
                .build();

        airportRepository.save(airport);
        Passengers john = Passengers.builder()
                .name("John Smith")
                .city("Boston")
                .number("613")
                .street("Flowers street")
                .zipCode("12345")
                .airport(airport).build();
        Passengers mike = Passengers.builder()
                .name("Michael Johnson")
                .airport(airport).build();

        ContactAddress contactAddress = ContactAddress.builder()
                .city("Sunnyvale")
                .country("USA")
                .state("CA")
                .number("27")
                .street("Wildwood avenue")
                .zipCode("95281")
                .passenger(john).build();

        Tickets ticket1 = Tickets.builder()
                .number("AA1234")
                .passengers(john).build();
        Tickets ticket2 = Tickets.builder()
                .number("BB5678")
                .passengers(john).build();
        Tickets ticket3 = Tickets.builder()
                .number("CC0987")
                .passengers(mike).build();


        john.addTickets(ticket1);
        john.addTickets(ticket2);
        mike.addTickets(ticket3);
        airport.addPassanger(john);
        airport.addPassanger(mike);
        passengersRepository.save(john);
        passengersRepository.save(mike);
        ticketsRepository.save(ticket1);
        ticketsRepository.save(ticket2);
        ticketsRepository.save(ticket3);
        contactAddressService.saveContactAddress(contactAddress);
    }
}
