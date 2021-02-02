package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DiscriminatorValue("ST")
public class Stock extends Investment {

    @Column(name = "SHARE_PRICE")
    private BigDecimal sharePrice;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;
}
