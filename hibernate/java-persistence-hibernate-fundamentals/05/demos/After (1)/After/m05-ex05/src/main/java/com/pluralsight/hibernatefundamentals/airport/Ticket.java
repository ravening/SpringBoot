package com.pluralsight.hibernatefundamentals.airport;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
