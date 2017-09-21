package com.example.crudexample.feature.employee.api.message.response;

import javax.validation.constraints.NotNull;

import com.example.crudexample.api.message.response.GenericSuccessResponse;
import com.example.crudexample.dto.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserResponse extends GenericSuccessResponse{
	@NotNull
	@JsonProperty("result")
	private EmployeeDTO employeeDTO;

	public GetUserResponse() {
	}

	/**
	 * @return the employeeDTO
	 */
	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	/**
	 * @param employeeDTO the employeeDTO to set
	 */
	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}
}
