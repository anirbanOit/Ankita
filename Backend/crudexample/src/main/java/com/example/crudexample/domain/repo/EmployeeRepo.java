package com.example.crudexample.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crudexample.domain.entity.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
