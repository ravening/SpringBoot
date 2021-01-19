package com.rakeshv.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name = "ABSTRACT_TICKETS")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  use this for single table per entity
//@DiscriminatorColumn(name = "TICKET_TYPE") use for single table per entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@MappedSuperclass used to indicate that it does not have its own persistance . used for base as non entity
// In this case remove table annotation

@Inheritance(strategy = InheritanceType.JOINED) // use this for joined subclass strategy

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // use this for table per concrete class
// no change needed in concrete class
public abstract class AbstractTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String number;

    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID")
    private Passengers passengers;

    public void setNumber(String number) {
        this.number = number;
    }
}
