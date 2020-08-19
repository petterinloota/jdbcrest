package com.pedatsku.jdbcrest.rest;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedatsku.jdbcrest.model.Employee;
import com.pedatsku.jdbcrest.service.EmployeeService;



@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService; 

    private static final String template = "Moi, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/info")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(template, name);
    }
    
    private void doAdd (String name, String id) {
		Employee emp = new Employee();
    	emp.setEmpId(id);
    	emp.setEmpName(name);    	    	
    	employeeService.insertEmployee(emp);    	
    }
    
    @GetMapping("/add")
    public String add(@RequestParam(value = "name") String name, @RequestParam(value = "id") String id) {
    	String response = "N/A";
    	
    	if ( Optional.ofNullable(id).orElse("").length() > 0) {
    		   // wanted valid non-empty-value
    		if ( Optional.ofNullable(name).orElse("").length() > 0) {  
    			doAdd(name, id);
    	    	response = "OK";
    		}
    	}

        return response;
    }
    
    @PostMapping("/add")
    public String addPost(@RequestBody String data) {
    	String response = "N/A";
    	
		if (Optional.ofNullable(data).orElse("").length() > 0) {
			// wanted valid non-empty-value
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				Employee employee = objectMapper.readValue(data, Employee.class);
				employeeService.insertEmployee(employee);
				response = "OK";
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

        return response;    	
    	
    }
    
    
}
	

