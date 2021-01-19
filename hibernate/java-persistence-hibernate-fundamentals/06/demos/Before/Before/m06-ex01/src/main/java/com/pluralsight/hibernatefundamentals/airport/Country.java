package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "COUNTRY")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE_NAME")
    private String codeName;

    public Country() {

    }

    public Country(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", codeName='" + codeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) &&
                Objects.equals(codeName, country.codeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, codeName);
    }
}
