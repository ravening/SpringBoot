package com.pluralsight.hibernatefundamentals.airport;

import javax.persistence.*;

@Entity
@Table(name = "MANAGERS")
public class Manager {
	@Id
	@GeneratedValue
	private int id;

	private String name;

    @OneToOne
	@JoinTable(name = "MANAGER_TO_DEPARTMENT", joinColumns = {
			@JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID", nullable = false)})
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
