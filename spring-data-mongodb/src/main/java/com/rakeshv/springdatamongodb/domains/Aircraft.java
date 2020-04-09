package com.rakeshv.springdatamongodb.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aircraft {
    private String model;
    private int nbSeats;
}
