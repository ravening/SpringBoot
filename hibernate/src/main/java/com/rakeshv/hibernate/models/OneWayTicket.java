package com.rakeshv.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @Table(name = "oneway_ticket") used for extending non entity
//@DiscriminatorValue("O") use this for single table per entity

@Table(name = "ONEWAY_TICKETS") // use this for joined subclass strategy. no change needed
public class OneWayTicket extends AbstractTicket {
    private LocalDate latestDepartureDate;
}
