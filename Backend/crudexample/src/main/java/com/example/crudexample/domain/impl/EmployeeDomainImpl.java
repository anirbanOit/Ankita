package com.example.crudexample.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudexample.domain.EmployeeDomain;
import com.example.crudexample.domain.entity.Employee;
import com.example.crudexample.domain.repo.EmployeeRepo;

@Service("employeeDomain")
public class EmployeeDomainImpl implements EmployeeDomain{
	@Autowired(required = true)
	private EmployeeRepo employeeRepo;
	
	@Override
	public List<Employee> getAllUsersProfile() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee addUser(Employee employee) {
		return employeeRepo.saveAndFlush(employee);
	}
	
	@Override
	public Employee getUser(String id) {
		return employeeRepo.findOne(Long.parseLong(id));
	}

	@Override
	public void deleteUser(String id) {
		employeeRepo.delete(Long.parseLong(id));
		return ;
	}
}
