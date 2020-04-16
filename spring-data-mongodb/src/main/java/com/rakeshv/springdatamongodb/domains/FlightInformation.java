package com.rakeshv.springdatamongodb.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("flights")
@Data
@AllArgsConstructor
@Builder
public class FlightInformation {
    @Id
    private String id;

//    @Indexed(unique = true)
//    private String internalId;

    @DBRef
    private Airport departure;

    @DBRef
    private Airport destination;

    private FlightType type;
    private boolean isDelayed;
    private int durationMin;
    private LocalDateTime departureDate;
    private Aircraft aircraft;

    @Transient
    private LocalDate createdAt;

    public FlightInformation() {
        this.createdAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "FlightInformation{" +
                "id='" + id + '\'' +
                ", departure=" + departure +
                ", destination=" + destination +
                ", type=" + type +
                ", isDelayed=" + isDelayed +
                ", durationMin=" + durationMin +
                ", departureDate=" + departureDate +
                ", aircraft=" + aircraft +
                ", createdAt=" + createdAt +
                '}';
    }
}
