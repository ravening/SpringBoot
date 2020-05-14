package com.rakeshv.networkoverview.models;

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
public class Vlan {
    @Id
    @GeneratedValue
    private Long id;
    private Long vlanid;

    private List<String> links;

    @Relationship(type = "VLAN", direction = Relationship.INCOMING)
    private VlanRelationship vlanRelationship;

    public void addInterface(Interface anInterface) {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }
        this.links.add(anInterface.toString());
    }
}
