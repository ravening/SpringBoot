package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "NUMBER")
    private String number;

    private List<Passenger> passengers = new ArrayList<>();

    public Ticket(String number) {
        this.number = number;
    }

    public Ticket() {

    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

}
