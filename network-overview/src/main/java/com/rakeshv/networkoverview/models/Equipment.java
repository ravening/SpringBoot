package com.rakeshv.networkoverview.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.annotation.typeconversion.EnumString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NodeEntity
public class Equipment {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String ipaddress;
    private String model;
    private List<String> links;
//    @EnumString(value = EquipmentType.class, lenient = true)
    private String type;
//    private EquipmentType type;

    @Relationship(type = "INTERFACE")
    private List<Interface> interfaces;

    public void addInterface(Interface anInterface) {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }

        this.links.add(anInterface.toString());
    }
}
