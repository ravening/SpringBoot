package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;

@Entity
@Table(name = "TICKETS")
@IdClass(TicketKey.class)
public class Ticket {

    @Id
    private String series;
    private String number;

    private String origin;
    private String destination;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
