package com.rakeshv.hibernate.utils;

import com.rakeshv.hibernate.models.Airport;
import com.rakeshv.hibernate.models.ContactAddress;
import com.rakeshv.hibernate.models.Department;
import com.rakeshv.hibernate.models.EmbeddableTicket;
import com.rakeshv.hibernate.models.EmbeddedAddress;
import com.rakeshv.hibernate.models.Manager;
import com.rakeshv.hibernate.models.Passengers;
import com.rakeshv.hibernate.models.Payment;
import com.rakeshv.hibernate.models.TicketKey;
import com.rakeshv.hibernate.models.Tickets;
import com.rakeshv.hibernate.repositories.AirportRepository;
import com.rakeshv.hibernate.repositories.DepartmentRepository;
import com.rakeshv.hibernate.repositories.EmbeddableTicketRepository;
import com.rakeshv.hibernate.repositories.ManagerRepository;
import com.rakeshv.hibernate.repositories.PassengersRepository;
import com.rakeshv.hibernate.repositories.PaymentRepository;
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
    @Autowired
    EmbeddableTicketRepository embeddableTicketRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {
        Airport airport = Airport.builder()
                .name("Test")
                .build();

        airportRepository.save(airport);
        EmbeddedAddress address = EmbeddedAddress.builder()
                .street("University street")
                .number("17")
                .zipCode("85281")
                .city("Arizona").build();
        Passengers john = Passengers.builder()
                .name("John Smith")
                .city("Boston")
                .number("613")
                .street("Flowers street")
                .zipCode("12345")
                .embeddedAddress(address)
                .airport(airport).build();
        Passengers mike = Passengers.builder()
                .name("Michael Johnson")
                .areaCode("302")
                .prefix("231")
                .lineNumber("123456")
                .embeddedAddress(address)
                .airport(airport).build();

        john.addAttribute("VIP", "yes");
        john.addAttribute("FREQUENT_FLYER", "yes");

        mike.addAttribute("isdisabled", "true");
        mike.addAttribute("specialmeals", "vegan");

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

        TicketKey key = TicketKey.builder()
                .series("AA")
                .number("1234").build();

//        Tickets ticket4 = Tickets.builder()
//                .ticketKeyId(key)
//                .origin("Bucharest")
//                .destination("London").build();

        Payment payment = Payment.builder()
                .tickets(ticket1)
                .amount(555).build();

        EmbeddableTicket embeddableTicket = EmbeddableTicket.builder()
                .series("EE")
                .number("4321")
                .origin("SFO")
                .destination("AMS").build();

        embeddableTicketRepository.save(embeddableTicket);
        john.addTickets(ticket1);
        john.addTickets(ticket2);
        mike.addTickets(ticket3);

        // manytomany
        john.addMultipleTickets(ticket1);
        john.addMultipleTickets(ticket2);
        john.addMultipleTickets(ticket3);
        mike.addMultipleTickets(ticket1);
        mike.addMultipleTickets(ticket2);
        mike.addMultipleTickets(ticket3);

        ticket1.addManyPassengers(john);
        ticket1.addManyPassengers(mike);
        ticket2.addManyPassengers(john);
        ticket2.addManyPassengers(mike);
        ticket3.addManyPassengers(john);
        ticket3.addManyPassengers(mike);

        airport.addPassanger(john);
        airport.addPassanger(mike);
        passengersRepository.save(john);
        passengersRepository.save(mike);
        ticketsRepository.save(ticket1);
        ticketsRepository.save(ticket2);
        ticketsRepository.save(ticket3);
//        ticketsRepository.save(ticket4);
        contactAddressService.saveContactAddress(contactAddress);
        paymentRepository.save(payment);

        Department accounting = Department.builder()
                .name("Accounting").build();
        Manager sam = Manager.builder()
                .name("Sam Smith")
                .department(accounting).build();

        departmentRepository.save(accounting);
        managerRepository.save(sam);
    }
}
