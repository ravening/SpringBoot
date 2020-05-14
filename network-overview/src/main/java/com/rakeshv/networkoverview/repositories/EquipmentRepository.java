package com.rakeshv.networkoverview.repositories;

import com.rakeshv.networkoverview.models.Equipment;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface EquipmentRepository extends Neo4jRepository<Equipment, Long> {

    Equipment findByNameEqualsIgnoreCase(@Param("name") String name);
    List<Equipment> findByNameLike(@Param("name") String name);

    Equipment findByIpaddress(@Param("ip") String ip);

    @Query("MATCH (e:Equipment)-[r:PORT]->(i:Interface) RETURN e,r,i")
    List<Equipment> equipmentGraph(@Param("name") String name);

    @Query(value = "MATCH (e:Equipment),(i:Interface)\n" +
            "WHERE e.name = :#{#name} AND i.name = :#{#switchname}\n" +
            "CREATE (e)-[r:PORT {name: e.name}]->(i)")
    @Transactional
    void createEquipmentRelationship(@Param("name") String name, @Param("switchname") String switchname);

    @Query("MATCH (e: Equipment)-[r:PORT]->(i:Interface) RETURN e,r,i LIMIT {limit}")
    Collection<Equipment> graph(@Param("limit") int limit);
}
