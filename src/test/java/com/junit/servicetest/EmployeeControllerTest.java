package com.junit.servicetest;

import com.junit.bean.Employee;
import com.junit.controller.EmployeeController;
import com.junit.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    void testGetAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        when(employeeService.getAllEmployee()).thenReturn(employeeList);

        List<Employee> result = employeeController.getAllEmployee();

        assertNotNull(result);
        assertEquals(employeeList, result);
        verify(employeeService, times(1)).getAllEmployee();
    }

    @Test
    void testGetEmployeeById() {
        Long id = 1L;
        Employee mockEmployee = new Employee();
        when(employeeService.getEmployeeById(id)).thenReturn(mockEmployee);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService, times(1)).getEmployeeById(id);
    }

    @Test
    void testGetEmployeeByIdNotFound() {
        Long id = 1L;
        when(employeeService.getEmployeeById(id)).thenReturn(null);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeService, times(1)).getEmployeeById(id);
    }

    @Test
    void testCreateEmployee() {
        Employee mockEmployee = new Employee();
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(mockEmployee);

        ResponseEntity<Employee> response = employeeController.createEmployee(mockEmployee);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService, times(1)).createEmployee(any(Employee.class));
    }

    @Test
    void testUpdateEmployee() {
        Long id = 1L;
        Employee mockEmployee = new Employee();
        when(employeeService.updateEmployee(eq(id), any(Employee.class))).thenReturn(mockEmployee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(id, mockEmployee);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEmployee, response.getBody());
        verify(employeeService, times(1)).updateEmployee(eq(id), any(Employee.class));
    }

    @Test
    void testUpdateEmployeeNotFound() {
        Long id = 1L;
        when(employeeService.updateEmployee(eq(id), any(Employee.class))).thenReturn(null);

        ResponseEntity<Employee> response = employeeController.updateEmployee(id, new Employee());

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(employeeService, times(1)).updateEmployee(eq(id), any(Employee.class));
    }
}
