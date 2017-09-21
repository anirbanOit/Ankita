package com.example.crudexample.exception.helper;

public enum ErrorCause {
	NA("NA", "No additional details available for this error"),
	
	/* Common: Error causes for Application Service, EMR service and Account service */	
	NO_DATA_FOUND("No_Data_Found", "No Records/Data found for the request."),
	SESSION_TOKEN("Session_Token","Session Token Provided is invalid."),
	ADMIN_ID("Admin_Id", "Admin_Id not found/invalid. Please provide either zero or appropriate Admin Id."),
	 NAME("NAME", "Name is required. Please provide valid name."),
	 AGE("AGE", "Age is missing or invalid. Please provide valid age."),
	 ADDRESS("ADDRESS", "Address is missing. Please provide address."),
	 ZIP("ZIP", "Zip is missing or invalid. Please provide valid zip.");
	
	private String message;

	private String desc;
	
	/**
	 * @param code
	 * @param message
	 * @param reason
	 * @param desc
	 */
	private ErrorCause(String message,String desc) {		
		this.message = message;		
		this.desc = desc;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
	public static ErrorCause findByMessage(final String message){
		for (ErrorCause errorCause : values()){
			if(errorCause.message.equalsIgnoreCase(message)){
				return errorCause;
			}
		}
		
		return null;
	}
}
