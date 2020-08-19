package com.pedatsku.jdbcrest.service;

import java.util.List;

import com.pedatsku.jdbcrest.model.Employee;

public interface EmployeeService {
	void insertEmployee(Employee emp);
	void insertEmployees(List<Employee> employees);
	void getAllEmployees();
	void getEmployeeById(String empid);
	void sampleFolk(); // this is a method for generating sample data to the db
}