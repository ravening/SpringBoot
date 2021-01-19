package com.rakeshv.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AssociationOverride;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(name = "RETURN_TICKET")
//@AssociationOverride(name = "passenger", joinColumns = @JoinColumn(name = "pass_id"))

//@DiscriminatorValue("R") use this for single table per entity

@Table(name = "RETURN_TICKETS") // use this for joined subclass strategy
public class ReturnTicket extends AbstractTicket {
    private LocalDate latestReturnDate;
}
