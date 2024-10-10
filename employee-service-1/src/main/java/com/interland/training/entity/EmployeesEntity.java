package com.interland.training.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "EMPLOYEE_DATA")

public class EmployeesEntity {

	@EmbeddedId
	private EmployeePK ids;

	private String firstName;

	private String lastName;

	private String emailId;

	private int age;

	private double salary;

	private String role;

	public EmployeesEntity() {
		super();
	}

	public EmployeesEntity(EmployeePK ids, String firstName, String lastName, String emailId, int age, double salary,
			String role) {
		super();
		this.ids = ids;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.age = age;
		this.salary = salary;
		this.role = role;
	}

	public EmployeePK getIds() {
		return ids;
	}

	public void setIds(EmployeePK ids) {
		this.ids = ids;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
