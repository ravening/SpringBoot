package com.pluralsight.hibernatefundamentals;

import com.pluralsight.hibernatefundamentals.airport.Address;
import com.pluralsight.hibernatefundamentals.airport.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernatefundamentals.m04.ex05");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Passenger john = new Passenger("John Smith");
        Address address = new Address();

        address.setStreet("Flowers Street");
        address.setNumber("3");
        address.setZipCode("012345");
        address.setCity("Boston");
        john.setAddress(address);

        em.persist(john);

        em.getTransaction().commit();
        emf.close();
    }
}
