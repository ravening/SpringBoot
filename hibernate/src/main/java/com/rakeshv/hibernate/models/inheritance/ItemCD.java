package com.rakeshv.hibernate.models.inheritance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ItemCD extends Item {

    @Column(name = "total_duration")
    private Double totalDuration;

    private String genre;
}
