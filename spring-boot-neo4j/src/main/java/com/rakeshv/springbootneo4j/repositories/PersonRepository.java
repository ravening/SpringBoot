package com.rakeshv.springbootneo4j.repositories;


import com.rakeshv.springbootneo4j.models.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Person getPersonByName(String name);
    Iterable<Person> findPersonByNameLike(String name);

    @Query("MATCH (am:Movie)<-[ai:ACTED_IN]-(p:Person)-[d:DIRECTED]->(dm:Movie) return p, collect(ai), collect(d), collect(am), collect(dm)")
    List<Person> getPersonsWhoActAndDirect();

//    @Query("MATCH (u:User)<-[r:RATED]-(m:Movie) RETURN u,r,m")
//    Collection<Person> getAllUsers();
}
