package com.rakeshv.springdatamongodb.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document("flights")
@Data
@AllArgsConstructor
@Builder
public class FlightInformation {
    @Id
    private String id;

    @Field("departure")
    @Indexed
    private String departureCity;

    @Field("destination")
    @Indexed
    private String destinationCity;

    private FlightType type;
    private boolean isDelayed;
    private int durationMin;
    private LocalDate departureDate;
    private Aircraft aircraft;

    @Transient
    private LocalDate createdAt;

    public FlightInformation() {
        this.createdAt = LocalDate.now();
    }

}
