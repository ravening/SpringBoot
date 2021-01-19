package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class OneWayTicket extends Ticket {

    private LocalDate latestDepartureDate;

    public LocalDate getLatestDepartureDate() {
        return latestDepartureDate;
    }

    public void setLatestDepartureDate(LocalDate latestDepartureDate) {
        this.latestDepartureDate = latestDepartureDate;
    }
}
