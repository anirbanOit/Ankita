package com.example.crudexample.handler;

import java.util.List;

import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.StatusDTO;

public interface EmployeeHandler {
	
	/**
	 * 
	 * @param errorCodePrefix
	 * @return EmployeeDTO
	 */
	List<EmployeeDTO> performGetUsersProfileDetail(String errorCodePrefix);

	/**
	 * 
	 * @param errorCodePrefix
	 * @param employeeDTO
	 * @return StatusDTO
	 */
	StatusDTO performAddUser(String errorCodePrefix, EmployeeDTO employeeDTO);

	/**
	 * 
	 * @param errorCodePrefix
	 * @return EmployeeDTO
	 */
	EmployeeDTO performGetUser(String errorCodePrefix, String id);
	
	/**
	 * 
	 * @param errorCodePrefix
	 * @param id
	 * @return StatusDTO
	 */
	StatusDTO performDeleteUser(String errorCodePrefix, String id);
}
