package com.pedatsku.jdbcrest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.pedatsku.jdbcrest.model.Employee;
import com.pedatsku.jdbcrest.service.EmployeeService;
import com.pedatsku.jdbcrest.service.impl.EmployeeServiceImpl;

@SpringBootApplication
public class SpringBootJdbcApplication {
	
	
	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootJdbcApplication.class, args);
		EmployeeService employeeService = context.getBean(EmployeeService.class);
		
		// Perhaps not here ...
		// employeeService.sampleFolk();
	}
}