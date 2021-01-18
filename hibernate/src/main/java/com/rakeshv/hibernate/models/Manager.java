package com.rakeshv.hibernate.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MANAGERS")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinTable(name = "MANAGER_TO_DEPARTMENT", joinColumns = {
            @JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID", nullable = false)}
    )
    private Department department;
}
