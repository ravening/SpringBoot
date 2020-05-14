package com.rakeshv.networkoverview.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NodeEntity
public class Interface {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonIgnoreProperties("interface")
    @Relationship(type = "INTERFACE", direction = Relationship.INCOMING)
    private ConnectionRelationship connectionRelationship;

    @Relationship(type = "VLAN")
    private Vlan vlan;

    @Override
    public String toString() {
        return "Interface(name=" + this.name + ")";
    }
//    @Relationship(type = "VLAN", direction = Relationship.INCOMING)
//    private Interface anotherInterface;
}
