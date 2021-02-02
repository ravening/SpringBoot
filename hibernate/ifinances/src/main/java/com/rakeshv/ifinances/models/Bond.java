package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DiscriminatorValue("BOND")
public class Bond extends Investment{

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "INTEREST_RATE")
    private BigDecimal interestRate;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "MATURITY_DATE")
    private Date maturityDate;

}
