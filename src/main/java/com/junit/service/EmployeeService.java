package com.junit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junit.bean.Employee;
import com.junit.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {

		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		Employee existingEmployee = employeeRepository.findById(id).orElse(null);
		if (existingEmployee != null) {
			existingEmployee.setName(updatedEmployee.getName());
			existingEmployee.setEmail(updatedEmployee.getEmail());
			existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
			return employeeRepository.save(existingEmployee);
		} else {
			return null;
		}
	}

}
