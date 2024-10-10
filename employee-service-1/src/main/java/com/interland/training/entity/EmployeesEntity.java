package com.interland.training.entity;

import com.interland.training.entity.validation.CustomNumberSize;
import com.interland.training.entity.validation.CustomSize;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "EMPLOYEE_DATA")

public class EmployeesEntity {

	@EmbeddedId
	private EmployeePK ids;

	@NotEmpty(message = "First Name cannot be empty")
	@CustomSize(minKey = "1", maxKey = "50", message = "Name must be Have atleast One Character")
	private String firstName;
	
	@NotEmpty(message = "Last Name cannot be empty")
	@CustomSize(minKey = "1", maxKey = "50", message = "Last Name must have atleast One Character")
	private String lastName;
	
	@NotEmpty(message = "Email Cannot be empty")
	@Email(message = "Invalid Email!")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email format is invalid")
	private String emailId;
	
	@NotNull(message = "Age cannot be null")
	@CustomNumberSize(maxKey = 100, minKey = 17, message = "Age must be above 17 Years")
	private int age;
	
	@NotNull(message = "Salary Cannot be Null")
	private double salary;
	
	@NotEmpty(message = "Role cannot be empty")
	@CustomSize(minKey = "1", maxKey = "30", message = "Role must have atleast One Character")
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
