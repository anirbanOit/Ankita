package com.example.crudexample.utils;

public enum APIGroupCode {
	/* ================== GENERIC ================== */
	GENERIC_SUCCESS("000", "GENERIC", "00", "SUCCESS"),
	GENERIC_TECHNICAL("000", "GENERIC", "01", "TECHNICAL"),
	GENERIC_BUSINESS("000", "GENERIC", "02", "BUSINESS"),
	
	
	/* ================== EMPLOYEE SERVICE ================== */
	EMPLOYEE_GET_ALL_USERS("001", "EMPLOYEE_GET_ALL_USERS", "00", "GET_ALL_USERS"),
	EMPLOYEE_ADD_USER("001", "EMPLOYEE_ADD_USER", "01", "ADD_USER"),
	EMPLOYEE_GET_USER("001", "EMPLOYEE_GET_USER", "02", "GET_USER"),
	EMPLOYEE_DELETE_USER("001", "EMPLOYEE_DELETE_USER", "03", "DELETE_USER");

	private String groupCode;
	private String groupName;
	private String apiCode;
	private String apiName;

	/**
	 * @param groupCode
	 * @param groupName
	 * @param apiCode
	 * @param apiName
	 * @param statusCode
	 * @param statusType
	 */
	private APIGroupCode(String groupCode, String groupName, String apiCode, String apiName) {
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.apiCode = apiCode;
		this.apiName = apiName;
	}

	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @return the apiCode
	 */
	public String getApiCode() {
		return apiCode;
	}

	/**
	 * @return the apiName
	 */
	public String getApiName() {
		return apiName;
	}

	/**
	 * @param groupCode
	 *            the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @param apiCode
	 *            the apiCode to set
	 */
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	/**
	 * @param apiName
	 *            the apiName to set
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
	/**
	 * 
	 * @param groupName
	 * @return enum
	 */
	public static APIGroupCode findByGroupAndApiName(final String groupName, final String apiName) {
		for (APIGroupCode apiGroupCode : values()) {
			if (apiGroupCode.groupName.equalsIgnoreCase(groupName) && apiGroupCode.apiName.equalsIgnoreCase(apiName)) {
				return apiGroupCode;
			}
		}

		return null;
	}
	
	public static APIGroupCode findByErrorCode(final String errorCode) {
		String groupCode = errorCode.substring(0, 3);
		String apiCode = errorCode.substring(3, 5);
		
		for (APIGroupCode apiGroupCode : values()) {
			if (apiGroupCode.groupCode.equalsIgnoreCase(groupCode) && apiGroupCode.apiCode.equalsIgnoreCase(apiCode)) {
				return apiGroupCode;
			}
		}

		return null;
	}
}
