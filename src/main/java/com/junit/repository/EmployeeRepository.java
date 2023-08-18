package com.junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junit.bean.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
