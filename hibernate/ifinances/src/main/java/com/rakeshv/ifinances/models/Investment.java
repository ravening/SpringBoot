package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "INVESTMENT_TYPE")
@Entity
@Table(name = "investment")
public abstract class Investment {

    @Id
    @GeneratedValue(generator = "key_generator", strategy = GenerationType.TABLE)
    @TableGenerator(table = "ifinances_keys", pkColumnName = "PK_NAME", pkColumnValue = "PK_VALUE", name = "key_generator")
    @Column(name = "INVESTMENT_ID")
    private Long investmentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PORTFOLIO_ID")
    private Portfolio portfolio;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "ISSUER")
    protected String issuer;

    @Column(name = "PURCHASE_DATE")
    protected Date purchaseDate;
}
