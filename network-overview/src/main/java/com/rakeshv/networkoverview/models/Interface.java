package com.rakeshv.networkoverview.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Builder
@NodeEntity
public class Interface {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String macAddress;
    private String ipAddress;
    private Long switchId;
    private String switchName;
    private List<Long> vlanList = new ArrayList<>();
    private String neighborPort;
    private String neighborHost;
    private List<Interface> neighbours;

    @Relationship(type = "PORT", direction = Relationship.INCOMING)
    private ConnectionRelationship connectionRelationship;

    @Relationship(type = "VLAN")
    private Vlan vlan;

    @Relationship(type = "DIRECT", direction = Relationship.UNDIRECTED)
    private Set<Interface> interfaceSet;

    @Override
    public String toString() {
        return "{'name': " + this.name + ", 'id': " + this.id + ", belongsto:" + switchId + "'vlanList:'" + this.getVlanList() + "}";
    }

    public void addVlan(Long id) {
        if (this.vlanList == null) {
            this.vlanList = new ArrayList<>();
        }
        this.vlanList.add(id);
    }

    public void addInterface(Interface anotherInterface) {
        if (this.interfaceSet == null) {
            this.interfaceSet = new HashSet<>();
        }
        if (!this.interfaceSet.contains(anotherInterface)) {
            this.interfaceSet.add(anotherInterface);
        }
    }

    public void addNeighbour(Interface anotherInterface) {
        if (this.neighbours == null) {
            this.neighbours = new ArrayList<>();
        }
        this.neighbours.add(anotherInterface);
    }
//    @Relationship(type = "VLAN", direction = Relationship.INCOMING)
//    private Interface anotherInterface;
}
