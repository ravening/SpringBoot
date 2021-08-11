package com.example.report.reportService.repository;

import com.example.report.reportService.model.Report;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nethmih on 06.05.19.
 */
@RepositoryRestResource(collectionResourceRel = "Report", path = "Report")
public interface ReportRepository extends Neo4jRepository<Report, Long> {

    @Query(value = "MATCH (a:Report),(b:Entity)\n" +
            "WHERE ID(a) = :#{#rid} AND b.name = :#{#entityName}\n" +
            "CREATE (a)-[r:BELONG]->(b)\n" +
            "RETURN r\n")
    @Transactional
    void createBelongRelationship(@Param("rid") Long rid, @Param("entityName") String entityName);
}
