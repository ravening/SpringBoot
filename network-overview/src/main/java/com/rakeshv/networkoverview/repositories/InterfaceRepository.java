package com.rakeshv.networkoverview.repositories;

import com.rakeshv.networkoverview.models.Interface;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource
public interface InterfaceRepository extends Neo4jRepository<Interface, Long> {
    @Query(value = "MATCH (i:Interface),(v:Vlan)\n" +
            "WHERE i.name = :#{#name} AND v.vlanid = :#{#vlanid}\n" +
            "CREATE (i)-[r:VLAN {name: v.vlanid}]->(v)\n" +
            "RETURN r\n")
    @Transactional
    void createVlanRelationship(@Param("name") String name, @Param("vlanid") Long vlanid);

    Interface findByName(@Param("name") String name);

    Interface findByNameAndSwitchName(@Param("name") String name, @Param("switchname") String switchname);

    List<Interface> findByNameLike(@Param("name") String name);

    Interface findByNameAndSwitchId(@Param("name") String name, @Param("id") Long id);
//    @Query(value = "MATCH (from:Interface),(to:Interface)\n" +
//            "WHERE from.name = :#{#fromname} AND to.name = :#{#toname}\n" +
//            "CREATE (from)-[r:VLAN {name: :#{#vlanid}}]->(to)\n" +
//            "RETURN r\n")
//    @Transactional
//    void createVlanRelationship(@Param("fromname") String fromname, @Param("toname") String toname, @Param("vlanid") Integer vlanid);
}
