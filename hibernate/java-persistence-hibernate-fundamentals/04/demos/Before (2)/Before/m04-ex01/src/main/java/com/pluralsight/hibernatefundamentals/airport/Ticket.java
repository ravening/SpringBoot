package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;

@Entity
@Table(name = "TICKETS")
public class Ticket {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "NUMBER")
    private String number;

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

}
