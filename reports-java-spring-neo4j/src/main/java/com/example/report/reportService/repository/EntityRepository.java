package com.example.report.reportService.repository;

import com.example.report.reportService.model.Entity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by nethmih on 06.05.19.
 */
@RepositoryRestResource(collectionResourceRel = "Entity", path = "Entity")
public interface EntityRepository extends Neo4jRepository<Entity, Long> {
}
