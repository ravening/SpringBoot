package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("R")
public class ReturnTicket extends Ticket {

    private LocalDate latestReturnDate;

    public LocalDate getLatestReturnDate() {
        return latestReturnDate;
    }

    public void setLatestReturnDate(LocalDate latestReturnDate) {
        this.latestReturnDate = latestReturnDate;
    }
}
