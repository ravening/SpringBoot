package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private SessionJpaRepository sessionJpaRepository;

    public Session create(Session session) {
        return sessionJpaRepository.saveAndFlush(session);
    }

    public Session update(Session session) {
        return sessionJpaRepository.saveAndFlush(session);
    }

    public void delete(Long id) {
        sessionJpaRepository.deleteById(id);
    }

    public Session find(Long id) {
        return sessionJpaRepository.getOne(id);
    }

    public List<Session> list() {
        return sessionJpaRepository.findAll();
    }

    public List<Session> getSessionsThatHaveName(String name) {
//        List<Session> ses = entityManager
//                .createQuery("select s from Session s where s.sessionName like :name")
//                .setParameter("name", "%" + name + "%").getResultList();
//        return ses;

        return sessionJpaRepository.findBySessionNameContains(name);
    }
}
