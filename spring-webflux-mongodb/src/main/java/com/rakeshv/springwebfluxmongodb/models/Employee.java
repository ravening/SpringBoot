package com.rakeshv.springwebfluxmongodb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Employee {
    @Id
    String id;
    String name;
    String department;
    private Long salary;
}
