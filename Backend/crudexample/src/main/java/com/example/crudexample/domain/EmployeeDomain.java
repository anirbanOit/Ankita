package com.example.crudexample.domain;

import java.util.List;

import com.example.crudexample.domain.entity.Employee;

public interface EmployeeDomain {
	
	/**
	 * 
	 * @return List<Employee>
	 */
	List<Employee> getAllUsersProfile();
	
	/**
	 * 
	 * @param employee
	 * @return Employee
	 */
	Employee addUser(Employee employee);

	/**
	 * 
	 * @param id
	 * @return Employee
	 */
	Employee getUser(String id);
	
	/**
	 *  
	 * @param id
	 */
	void deleteUser(String id);
}
