package com.junit.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.junit.bean.Employee;
import com.junit.repository.EmployeeRepository;
import com.junit.service.EmployeeService;

class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllEmployee() {
		// Create some sample employees
		Employee employee1 = new Employee(1L, "John Doe", "john.doe@example.com", 1234567890L);
		Employee employee2 = new Employee(2L, "Jane Smith", "jane.smith@example.com", 9876543210L);
		List<Employee> employees = new ArrayList<>();
		employees.add(employee1);
		employees.add(employee2);

		// Mock the behavior of employeeRepository.findAll() to return the sample
		// employees
		when(employeeRepository.findAll()).thenReturn(employees);

		// Test the service method
		List<Employee> result = employeeService.getAllEmployee();

		// Verify the results
		assertEquals(2, result.size());
		assertEquals(employee1.getName(), result.get(0).getName());
		assertEquals(employee2.getName(), result.get(1).getName());
	}

	@Test
	void testGetEmployeeById_ExistingId() {
		// Create a sample employee
		Employee employee = new Employee(1L, "John Doe", "john.doe@example.com", 1234567890L);

		// Mock the behavior of employeeRepository.findById() to return the sample
		// employee
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

		// Test the service method
		Employee result = employeeService.getEmployeeById(1L);

		// Verify the result
		assertEquals(employee.getName(), result.getName());
	}

	@Test
	// @Disabled
	void testGetEmployeeById_NonExistingId() {
		// Mock the behavior of employeeRepository.findById() to return null
		// (non-existing employee)
		when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

		// Test the service method
		Employee result = employeeService.getEmployeeById(99L);

		// Verify the result
		assertEquals(null, result);
	}

	@Test
	void testCreateEmployee() {
		// Create a sample employee to be saved
		Employee newEmployee = new Employee(null, "New Employee", "new.employee@example.com", 9876543210L);

		// Create a sample employee with the ID after being saved
		Employee savedEmployee = new Employee(1L, "New Employee", "new.employee@example.com", 9876543210L);

		// Mock the behavior of employeeRepository.save() to return the saved employee
		when(employeeRepository.save(newEmployee)).thenReturn(savedEmployee);

		// Test the service method
		Employee result = employeeService.createEmployee(newEmployee);

		// Verify the result
		assertEquals(savedEmployee.getEmployeeId(), result.getEmployeeId());
		assertEquals(newEmployee.getName(), result.getName());
		assertEquals(newEmployee.getEmail(), result.getEmail());
		assertEquals(newEmployee.getPhoneNumber(), result.getPhoneNumber());
	}

	@Test
	void testUpdateEmployee_ExistingId() {
		// Create a sample existing employee
		Employee existingEmployee = new Employee(1L, "Existing Employee", "existing.employee@example.com", 1234567890L);

		// Create a sample updated employee with new details
		Employee updatedEmployee = new Employee(1L, "Updated Employee", "updated.employee@example.com", 9876543210L);

		// Mock the behavior of employeeRepository.findById() to return the existing
		// employee
		when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));

		// Mock the behavior of employeeRepository.save() to return the updated employee
		when(employeeRepository.save(existingEmployee)).thenReturn(updatedEmployee);

		// Test the service method
		Employee result = employeeService.updateEmployee(1L, updatedEmployee);

		// Verify the result
		assertEquals(existingEmployee.getEmployeeId(), result.getEmployeeId());
		assertEquals(updatedEmployee.getName(), result.getName());
		assertEquals(updatedEmployee.getEmail(), result.getEmail());
		assertEquals(updatedEmployee.getPhoneNumber(), result.getPhoneNumber());
	}

	@Test
	// @Disabled
	void testUpdateEmployee_NonExistingId() {
		// Create a sample non-existing employee with new details
		Employee updatedEmployee = new Employee(99L, "Updated Employee", "updated.employee@example.com", 9876543210L);

		// Mock the behavior of employeeRepository.findById() to return null
		// (non-existing employee)
		when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

		// Test the service method
		Employee result = employeeService.updateEmployee(99L, updatedEmployee);

		// Verify the result (should be null as the employee with ID 99 does not exist)
		assertEquals(null, result);
	}
}
