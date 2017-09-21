package com.example.crudexample.feature.employee.api.message.response;

import javax.validation.constraints.NotNull;

import com.example.crudexample.api.message.response.GenericSuccessResponse;
import com.example.crudexample.dto.StatusDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserResponse extends GenericSuccessResponse{
	
	@NotNull
	@JsonProperty("result")
	private StatusDTO statusDTO;

	public StatusDTO getStatusDTO() {
		return statusDTO;
	}

	public void setStatusDTO(StatusDTO statusDTO) {
		this.statusDTO = statusDTO;
	}

}
