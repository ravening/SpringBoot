package com.rakeshv.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "EMBEDDABLE_TICKET")
@IdClass(TicketKey.class) // indicates that id is coming from ticketkey class
public class EmbeddableTicket {

    @Id
    private String series;
    private String number;
    private String origin;
    private String destination;
}
