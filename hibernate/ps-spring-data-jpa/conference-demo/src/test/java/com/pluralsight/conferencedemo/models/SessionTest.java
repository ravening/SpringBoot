package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SessionJpaRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SessionTest {
    @Autowired
    private SessionRepository repository;

    @Autowired
    private SessionJpaRepository sessionJpaRepository;

    @Test
    public void test() throws Exception {
        List<Session> sessions = repository.getSessionsThatHaveName("Java");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJapNot() {
        List<Session> sessions = sessionJpaRepository.findBySessionLengthNot(30);
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJapNotLike() {
        List<Session> sessions = sessionJpaRepository.findBySessionNameNotLike("Java%");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJapLessThan() {
        List<Session> sessions = sessionJpaRepository.findBySessionLengthLessThan(45);
        assertTrue(sessions.size() > 0);
    }
}
