package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;

@Entity
@Table(name = "MANAGERS")
public class Manager {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	private Department department;

	public Manager(String name) {
		this.name = name;
	}

	public Manager() {
	}

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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
