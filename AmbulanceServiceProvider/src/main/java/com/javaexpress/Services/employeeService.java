package com.javaexpress.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.Models.Employee;
import com.javaexpress.Repos.EmployeeRepo;

@Service
public class employeeService {

    @Autowired
    private EmployeeRepo employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
}
