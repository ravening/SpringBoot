package com.example.report.reportService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by nethmih on 06.05.19.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Entity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;

    @Relationship(type = "BELONG", direction = Relationship.INCOMING)
    private BelongRelationship belongRelationship;
}
