package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TicketKey implements Serializable {

    private String series;
    private String number;

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
}
