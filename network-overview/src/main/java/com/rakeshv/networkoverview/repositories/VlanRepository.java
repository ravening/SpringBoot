package com.rakeshv.networkoverview.repositories;

import com.rakeshv.networkoverview.models.Vlan;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VlanRepository extends Neo4jRepository<Vlan, Long> {
    Vlan findByVlanid(Long id);
}
