package com.pluralsight.hibernatefundamentals;

import com.pluralsight.hibernatefundamentals.airport.Ticket;
import com.pluralsight.hibernatefundamentals.airport.TicketKey;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.m03.ex05");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        TicketKey key = new TicketKey();
        key.setSeries("AA");
        key.setNumber("1234");

        Ticket ticket1 = new Ticket();
        ticket1.setId(key);
        ticket1.setOrigin("Bucharest");
        ticket1.setDestination("London");
        em.persist(ticket1);

        em.getTransaction().commit();
        emf.close();
    }
}
