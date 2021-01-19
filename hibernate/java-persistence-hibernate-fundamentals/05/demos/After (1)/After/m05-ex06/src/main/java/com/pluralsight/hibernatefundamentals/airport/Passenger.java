package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;

@Entity
@Table(name = "PASSENGERS")
public class Passenger {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @Convert(converter = VipConverter.class)
    private boolean vip;

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

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

}
