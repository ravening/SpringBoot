package com.rakeshv.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TICKETS")
public class Tickets  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER")
    private String number;

//    @EmbeddedId
//    private TicketKey ticketKeyId;
//    private String origin;
//    private String destination;

    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID")
    private Passengers passengers;

    @ManyToMany
    @JoinTable(name = "MANYMANY") // if name not mentioned then table name will be
    // TICKETS_MANY_PASSENGERS
    private List<Passengers> manyPassengers = new ArrayList<>();

    public void addManyPassengers(Passengers p) {
        if (this.manyPassengers == null) {
            this.manyPassengers = new ArrayList<>();
        }
        this.manyPassengers.add(p);
    }

    public List<Passengers> getManyPassenger() {
        return Collections.unmodifiableList(manyPassengers);
    }
}
