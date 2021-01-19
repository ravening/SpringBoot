package com.pluralsight.hibernatefundamentals.airport;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TICKETS")
public abstract class Ticket {
    @Id
    @GeneratedValue
    private int id;
    private String number;

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
