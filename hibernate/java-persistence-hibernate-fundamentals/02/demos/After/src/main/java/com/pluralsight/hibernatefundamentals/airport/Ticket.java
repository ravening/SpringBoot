package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;

@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NUMBER")
    private String number;

    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID")
    private Passenger passenger;

    public Ticket(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public Ticket() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
