package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.TicketTypeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TicketTypeTest {
    @Autowired
    TicketTypeJpaRepository ticketTypeJpaRepository;

    @Test
    public void testJpaTrue() {
        List<TicketType> ticketTypes = ticketTypeJpaRepository.findByIncludesWorkshopTrue();
        assertTrue(ticketTypes.size() > 0);
    }

    @Test
    public void testJpaFalse() {
        List<TicketType> ticketTypes = ticketTypeJpaRepository.findByIncludesWorkshopFalse();
        assertTrue(ticketTypes.size() > 0);
    }
}
