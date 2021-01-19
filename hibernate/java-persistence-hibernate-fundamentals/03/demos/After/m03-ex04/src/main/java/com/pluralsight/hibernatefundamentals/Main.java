package com.pluralsight.hibernatefundamentals;

import com.pluralsight.hibernatefundamentals.airport.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.m03.ex04");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Ticket ticket1 = new Ticket();
        ticket1.setSeries("AA");
        ticket1.setNumber("1234");
        ticket1.setOrigin("Bucharest");
        ticket1.setDestination("London");
        em.persist(ticket1);

        em.getTransaction().commit();
        emf.close();
    }
}
