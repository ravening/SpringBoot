package com.rakeshv.springbootneo4j.repositories;

import com.rakeshv.springbootneo4j.models.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long> {
    Movie getMovieByTitle(String title);
    Iterable<Movie> findMovieByTitleLike(String title);

    @Query("MATCH (m:Movie)<-[:ACTED_IN]-(a:Person)  RETURN m.title as movie, collect(a.name) as cast LIMIT {limit}")
    List<Map<String,Object>> graph(@Param("limit") int limit);
}
