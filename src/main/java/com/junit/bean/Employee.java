package com.junit.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "EMPLOYEE_ID", unique = true, nullable = false)

	private Long employeeId;

	@Column(name = "EMPLOYEE_NAME")
	private String name;

	@Column(name = "EMPLOYEE_EMAIL")
	private String email;

	@Column(name = "EMPLOYEE_PHONENUMBER")

	private Long phoneNumber;

	public Employee(Long employeeId, String name, String email, Long phoneNumber) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Employee() {
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
