package com.example.report.reportService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by nethmih on 06.05.19.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private String date;

    @Relationship(type = "REPORT", direction = Relationship.INCOMING)
    private ReportRelationShip reportRelationShip;

    @Relationship(type = "BELONG")
    private Entity entity;






}
