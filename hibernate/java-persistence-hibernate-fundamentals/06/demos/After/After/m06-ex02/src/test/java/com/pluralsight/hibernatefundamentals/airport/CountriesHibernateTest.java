package com.pluralsight.hibernatefundamentals.airport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.hibernatefundamentals.airport.CountryService.COUNTRY_INIT_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class CountriesHibernateTest {

    @Autowired
    private CountryService countryService;

    private List<Country> expectedCountryList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        countryService.init();
        initExpectedCountryLists();
    }

    @Test
    public void testCountryList() {
        List<Country> countryList = countryService.getAllCountries();
        assertNotNull(countryList);
        assertEquals(COUNTRY_INIT_DATA.length, countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @AfterEach
    public void dropDown() {
        countryService.clear();
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
        }
    }
}
