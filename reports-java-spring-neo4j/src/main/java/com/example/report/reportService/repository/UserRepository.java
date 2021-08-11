package com.example.report.reportService.repository;

import com.example.report.reportService.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nethmih on 06.05.19.
 */

@RepositoryRestResource(collectionResourceRel = "User", path = "User")
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query(value = "MATCH (a:User),(b:Report)\n" +
            "WHERE a.username = :#{#username} AND ID(b) = :#{#reportId}\n" +
            "CREATE (a)-[r:REPORT]->(b)")
    @Transactional
    void createReportRelationship(@Param("username") String username, @Param("reportId") Long reportId);
}
