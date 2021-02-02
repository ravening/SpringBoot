package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "market")
public class Market {
    @Id
    @GeneratedValue
    @Column(name = "MARKET_ID")
    private Long marketId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "CURRENCY_NAME", referencedColumnName = "NAME"),
            @JoinColumn(name = "COUNTRY_NAME", referencedColumnName = "COUNTRY_NAME")
    })
    private Currency currency;

    @Column(name = "MARKET_NAME")
    private String marketName;
}
