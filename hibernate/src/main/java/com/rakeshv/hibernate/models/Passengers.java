package com.rakeshv.hibernate.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PASSENGERS")
//@SecondaryTable(name = "ADDRESSES", pkJoinColumns = @PrimaryKeyJoinColumn(name = "PASSENGER_ID", referencedColumnName = "PASSENGER_ID"))
@SecondaryTables(
        {
                @SecondaryTable(name = "ADDRESSES",
                        pkJoinColumns = @PrimaryKeyJoinColumn(name = "PASSENGER_ID", referencedColumnName = "PASSENGER_ID")),
                @SecondaryTable(name = "PHONES",
                    pkJoinColumns = @PrimaryKeyJoinColumn(name = "PASSENGER_ID", referencedColumnName = "PASSENGER_ID"))
        }
)
public class Passengers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PASSENGER_ID")
    private Long id;

    @Column(name = "PASSENGER_NAME")
    private String name;

    @OneToMany(mappedBy = "passenger")
    @JsonBackReference
    private List<ContactAddress> contactAddress;

    @Column(name = "STREET", table = "ADDRESSES", columnDefinition = "varchar(255) not null")
    private String street;

    @Column(name = "NUMBER", table = "ADDRESSES", columnDefinition = "varchar(10) not null")
    private String number;

    @Column(name = "ZIP_CODE", table = "ADDRESSES", columnDefinition = "varchar(10) not null")
    private String zipCode;

    @Column(name = "CITY", table = "ADDRESSES", columnDefinition = "varchar(25) not null")
    private String city;

    @Column(name = "AREA_CODE", table = "PHONES", columnDefinition = "varchar(5) not null")
    private String areaCode;

    @Column(name = "PREFIX", table = "PHONES", columnDefinition = "varchar(5) not null")
    private String prefix;

    @Column(name = "LINE_NUMBER", table = "PHONES", columnDefinition = "varchar(10) not null")
    private String lineNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AIRPORT_ID")
    private Airport airport;

    @OneToMany(mappedBy = "passengers")
    // this is to avoid n+1 queries problem
    @Fetch(FetchMode.SUBSELECT)
    private List<Tickets> tickets;

    public void addTickets(Tickets ticket) {
        if (tickets == null) {
            tickets = new ArrayList<>();
        }
        this.tickets.add(ticket);
    }

    public List<Tickets> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
