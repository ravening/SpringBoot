package com.rakeshv.networkoverview.models;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConnectionsCsv {
    @CsvBindByName(column = "sourcenode")
    private String sourceNode;
    @CsvBindByName(column = "sourceport")
    private String sourcePort;
    @CsvBindByName(column = "targetnode")
    private String targetNode;
    @CsvBindByName(column = "targetport")
    private String targetPort;
}
