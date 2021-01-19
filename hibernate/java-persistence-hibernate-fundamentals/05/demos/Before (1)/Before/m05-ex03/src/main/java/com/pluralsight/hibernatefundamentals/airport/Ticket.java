package com.pluralsight.hibernatefundamentals.airport;


public abstract class Ticket {
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
