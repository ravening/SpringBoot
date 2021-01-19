package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "DEPARTMENTS")
public class Department {
    @Id
    @GeneratedValue
    private int id;
    
    private String name;

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
