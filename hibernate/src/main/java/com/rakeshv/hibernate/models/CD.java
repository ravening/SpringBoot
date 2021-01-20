package com.rakeshv.hibernate.models;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CD {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 3000)
    private String description;

    @Column(name = "unit_cost")
    private Double unitCost;

    @Column(name = "total_duration")
    private Double totalDuration;

    private String genre;

    @OneToMany(cascade = CascadeType.PERSIST)
    // if cascade is present i dont need to save musicians separately
    @JoinColumn(name = "cd_id")
    private Set<Musician> musicians = new HashSet<>();
}
