package com.rakeshv.networkoverview.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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
    private String type;

    @Relationship(type = "PORT")
    private List<Interface> interfaces;

    public void addInterface(Interface anInterface) {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            String result = mapper.writeValueAsString(anInterface);
            this.links.add(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
