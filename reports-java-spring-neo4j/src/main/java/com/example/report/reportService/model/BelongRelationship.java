package com.example.report.reportService.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by nethmih on 06.05.19.
 */

@AllArgsConstructor
@NoArgsConstructor
class BelongRelationship {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Report report;

    @EndNode
    private Entity entity;
}
