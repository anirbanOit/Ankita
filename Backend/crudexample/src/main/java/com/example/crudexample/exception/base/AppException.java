package com.example.crudexample.exception.base;

import com.example.crudexample.exception.helper.ExceptionDetail;

public abstract class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ExceptionDetail exceptionDetail;

	private int httpStatusCode;

	/**
	 * @param status
	 * @param errorCode
	 * @param httpStatusCode
	 */
	public AppException() {
		super();
	}

	/**
	 * @param exceptionDetail
	 */
	public AppException(final ExceptionDetail exceptionDetail, final int httpStatusCode) {
		super();

		this.exceptionDetail = exceptionDetail;
		this.httpStatusCode = httpStatusCode;
	}

	/**
	 * @return the exceptionDetail
	 */
	public ExceptionDetail getExceptionDetail() {
		return exceptionDetail;
	}

	/**
	 * @return the httpStatusCode
	 */
	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	/**
	 * @param exceptionDetail the exceptionDetail to set
	 */
	public void setExceptionDetail(ExceptionDetail exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}

	/**
	 * @param httpStatusCode the httpStatusCode to set
	 */
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
}
