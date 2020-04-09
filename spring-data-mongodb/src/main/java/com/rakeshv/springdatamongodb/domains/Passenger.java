package com.rakeshv.springdatamongodb.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passenger {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private int age;
    private String nationality;
}
