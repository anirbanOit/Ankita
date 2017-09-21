package com.example.crudexample.exception.helper;

public enum ResponseCode {
	/* ================= SERVICE: GENERIC - START ================= */
	/* Generic Success */
	SUCCESS("000", "SUCCESS", "Operation performed successfully.", "Requested operation completed successfully."),
	
	/* Generic Error: Technical */
	UNCAUGHT("000", "UNCAUGHT", "Server Error [CRITICAL]: Uncaught error. Please try again later.", "Critical: Uncaught error. Any error which is not handled explicitly."),
	PARSE_ERROR("019", "PARSE_ERROR", "Server Error: Failed to parse request/response. Please try again later.", "Failed to parse third party server API response. Can also occur due to internal server issues."),
	CONSTRAINT_VIOLATION("020", "CONSTRAINT_VIOLATION", "Database constraint violated. Please provide valid entry.", "Databse constraint violated. Either entity attribute is unique (hence forbidding duplicate entry) or some other attribute constraint violation occured."),
	
	/* Generic Error: Business */
	MANDATORY_PARAMETER("101", "MANDATORY_PARAMETER", "Mandatory parameter missing. Please follow request format as per documentation.", "A mandatory parameter is missing from or is null/blank in the JSON request body. Please check the request body with published API specifications."),
	INVALID_ENCRYPTION("104", "INVALID_ENCRYPTION", "Failed to encrypt/decrypt data.", "Failed to encrypt/decrypt data. Probably encryption key is wrong or invalid input."),
	UNAUTHORIZED_ACCESS("111", "UNAUTHORIZED_ACCESS", "Access denied. User provided invalid credentials.", "User tried to perform an operation by providing invalid credentials. User is not recognized by the system to allow the same."),
	NOT_FOUND("118", "NOT_FOUND", "No data found. Please try again later.", "Failed to locate requested data. This means a search request did not produce any result(s) from DB query/third party API call."),
	OPERATION_FAILED("119", "OPERATION_FAILED", "Failed to perform requested operation. Please try again later.", "Failed to perform requested operation. Something went wrong due to server related issues."),
	FORBIDDEN("121", "FORBIDDEN", "Access denied. Unable to perform this operation.", "User don't have proper access permissions or provision to perform given operation. Please contact front desk/admin."),
	INVALID_VALUE("122", "INVALID_VALUE", "Field value not permitted. Please provide valid input.", "Input field valid is wrong. Please provide appropriate field value for the given field type."),

	/* ================= SERVICE: GENERIC - END ================= */
	
	/* ================= SERVICE: EMR SERVICE - START ================= */
	/* Generic Error */
	
	/* API: Create User */
	USER_ALREADY_REGISTERED("000", "USER_ALREADY_REGISTERED", "User already registered. Please login.", "User already registered with required EMR(s). Failed to create user."),
	
	/* API: Get Document */
	
	/* API: Upload Document */
	
	/* API: Update Patient Pharmacy */
	
	/* ================= SERVICE: EMR SERVICE - END ================= */
	
	/* ================= SERVICE: ACCOUNT SERVICE - START ================= */
	/* Generic Error */
		
	/* ================= SERVICE: ACCOUNT SERVICE - END ================= */
	;
	
	private String code;
	private String message;
	private String reason;
	private String desc;
	
	/**
	 * @param code
	 * @param message
	 * @param reason
	 * @param desc
	 */
	private ResponseCode(String code, String message, String reason, String desc) {
		this.code = code;
		this.message = message;
		this.reason = reason;
		this.desc = desc;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * 
	 * @param message
	 * @return enum
	 */
	public static ResponseCode findByMessage(final String message){
		for (ResponseCode responseCode : values()){
			if(responseCode.message.equalsIgnoreCase(message)){
				return responseCode;
			}
		}
		
		return null;
	}
}
