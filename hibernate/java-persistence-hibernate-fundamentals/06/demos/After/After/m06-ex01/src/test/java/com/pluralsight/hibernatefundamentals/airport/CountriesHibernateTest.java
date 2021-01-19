package com.pluralsight.hibernatefundamentals.airport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountriesHibernateTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    private List<Country> expectedCountryList = new ArrayList<>();

    public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"}, {"Canada", "CA"}, {"France", "FR"},
            {"Germany", "DE"}, {"Italy", "IT"}, {"Japan", "JP"}, {"Romania", "RO"},
            {"Russian Federation", "RU"}, {"Spain", "ES"}, {"Switzerland", "CH"},
            {"United Kingdom", "UK"}, {"United States", "US"}};

    @BeforeEach
    public void setUp() {
        initExpectedCountryLists();

        emf = Persistence.createEntityManagerFactory("hibernatefundamentals.m06.ex01");
        em = emf.createEntityManager();

        em.getTransaction().begin();

        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            em.persist(country);
        }

        em.getTransaction().commit();

    }

    @Test
    public void testCountryList() {
        List<Country> countryList = em.createQuery("select c from Country c").getResultList();
        assertNotNull(countryList);
        assertEquals(COUNTRY_INIT_DATA.length, countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }

    }

    @AfterEach
    public void dropDown() {
        em.close();
        emf.close();
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
        }
    }
}
