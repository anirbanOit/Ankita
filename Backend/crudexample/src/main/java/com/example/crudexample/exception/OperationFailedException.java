package com.example.crudexample.exception;

import com.example.crudexample.exception.base.AppTechnicalException;
import com.example.crudexample.exception.helper.ExceptionDetail;
import com.example.crudexample.utils.constant.AppConstant;

public class OperationFailedException extends AppTechnicalException {
	private static final long serialVersionUID = -6538006142999778827L;

	/**
	 * 
	 * @param exceptionDetail
	 */
	public OperationFailedException(final ExceptionDetail exceptionDetail) {
		super(exceptionDetail, AppConstant.HTTP_STATUS_INTERNAL_SERVER_ERROR);
	}
}
