package com.pluralsight.hibernatefundamentals;

import com.pluralsight.hibernatefundamentals.airport.Department;
import com.pluralsight.hibernatefundamentals.airport.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernatefundamentals.m04.ex03");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Manager john = new Manager("John Smith");
        Department accounting = new Department();
        accounting.setName("Accounting");

        john.setDepartment(accounting);

        em.persist(john);
        em.persist(accounting);

        em.getTransaction().commit();
        emf.close();
    }
}
