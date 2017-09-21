package com.example.crudexample.feature.employee.bean;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.dto.EmployeeDTO;

public interface EmployeeBean {
	
	/**
	 * 
	 * @param accessorId
	 * @param sessionToken
	 * @return AbstractResponse - List of all Users
	 */
	AbstractResponse processGetAllUserProfile(String accessorId, String sessionToken);

	/**
	 * 
	 * @param sessionToken
	 * @param employeeDTO
	 * @return AbstractResponse - Add User
	 */
	AbstractResponse processAddUser(String sessionToken, EmployeeDTO employeeDTO);

	
	/**
	 * 
	 * @param accessorIdStr
	 * @param sessionToken
	 * @return AbstractResponse - Get a single user
	 */
	AbstractResponse processGetUser(String accessorIdStr, String sessionToken, String id);
	
	/**
	 * 
	 * @param sessionToken
	 * @param accessorIdStr
	 * @param id
	 * @return AbstractResponse - Delete User
	 */
	AbstractResponse processDeleteUser(String sessionToken, String accessorIdStr, String id);
	
}
