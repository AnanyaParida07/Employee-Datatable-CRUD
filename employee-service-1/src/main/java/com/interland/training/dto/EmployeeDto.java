package com.interland.training.dto;

import com.interland.training.entity.validation.CustomNumberSize;
import com.interland.training.entity.validation.CustomSize;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EmployeeDto {

	@NotNull(message = "ID Cannot be Null")
	@CustomNumberSize(maxKey = 999, minKey = 100, message = "EMP-ID Must be of 3 digits")
	private int empId;

	@CustomNumberSize(maxKey = 999, minKey = 100, message = "DEPT-ID Must be of 3 digits")
	@NotNull(message = "ID Cannot be Null")
	private int deptId;

	@NotEmpty(message = "First Name cannot be empty")
	@CustomSize(minKey = "1", maxKey = "50", message = "Name must be Have atleast One Character")
	private String firstName;

	@NotEmpty(message = "Last Name cannot be empty")
	@CustomSize(minKey = "1", maxKey = "50", message = "Last Name must have atleast One Character")
	private String lastName;

	@NotNull(message = "Age cannot be null")
	@CustomNumberSize(maxKey = 100, minKey = 17, message = "Age must be above 17 Years")
	private int age;

	@NotEmpty(message = "Email Cannot be empty")
	@Email(message = "Invalid Email!")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email format is invalid")
	private String emailId;

	@NotNull(message = "Salary Cannot be Null")
	private double salary;

	@NotEmpty(message = "Role cannot be empty")
	@CustomSize(minKey = "1", maxKey = "30", message = "Role must have atleast One Character")
	private String role;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
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
