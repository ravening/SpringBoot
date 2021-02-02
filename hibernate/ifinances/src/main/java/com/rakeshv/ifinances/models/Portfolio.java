package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @GeneratedValue
    @Column(name = "PORTFOLIO_ID")
    private Long portfolioId;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "portfolio")
    private List<Investment> investments = new ArrayList<>();

    public List<Investment> getInvestments() {
        if (investments == null)
            investments = new ArrayList<>();
        return investments;
    }
}
