package com.example.report.reportService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nethmih on 06.05.19.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ReportRelationShip {
    @Id
    @GeneratedValue
    private Long id;

    private List<String> reports = new ArrayList<>();

    @StartNode
    private User user;

    @EndNode
    private Report report;

}
