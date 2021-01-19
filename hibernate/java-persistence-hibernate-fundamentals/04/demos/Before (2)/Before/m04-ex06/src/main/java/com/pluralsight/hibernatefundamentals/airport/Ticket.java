package com.pluralsight.hibernatefundamentals.airport;

public class Ticket {

    private String number;

    public Ticket(String number) {
        this.number = number;
    }

    public Ticket() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
