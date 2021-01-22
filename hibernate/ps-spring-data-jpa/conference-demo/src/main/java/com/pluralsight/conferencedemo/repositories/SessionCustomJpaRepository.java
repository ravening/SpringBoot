package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionCustomJpaRepository {
    List<Session> customGetSessions();
}
