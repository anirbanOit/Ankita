package com.example.crudexample.api.message.response;

import com.example.crudexample.utils.constant.AppConstant;

public class GenericSuccessResponse extends AbstractResponse {

	public GenericSuccessResponse() {
		super.setStatus(AppConstant.API_STATUS_OK);
		super.setStatusCode(AppConstant.GENERIC_SUCCESS_CODE);
		super.setStatusMessage(AppConstant.API_STATUS_OK);
	}

	/**
	 * @param statusMessage
	 */
	public GenericSuccessResponse(final String statusMessage) {
		super.setStatus(AppConstant.API_STATUS_OK);
		super.setStatusCode(AppConstant.GENERIC_SUCCESS_CODE);
		super.setStatusMessage(statusMessage);
	}
}
