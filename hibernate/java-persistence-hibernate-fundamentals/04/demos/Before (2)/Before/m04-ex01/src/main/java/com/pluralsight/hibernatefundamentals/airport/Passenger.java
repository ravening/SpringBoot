package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "PASSENGERS")
public class Passenger {

    @Id
    @GeneratedValue
    @Column(name = "PASSENGER_ID")
    private int id;

    @Column(name = "PASSENGER_NAME")
    private String name;

    public Passenger(String name) {
        this.name = name;
    }

    public Passenger() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
