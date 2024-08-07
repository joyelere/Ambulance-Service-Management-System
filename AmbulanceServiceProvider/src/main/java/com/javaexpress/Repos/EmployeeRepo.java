package com.javaexpress.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaexpress.Models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
