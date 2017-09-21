package com.example.crudexample.handler;

public interface RequestValidator {
	
	/**
	 * 
	 * @param token
	 * @param parentMethod
	 * @param errorCodePrefix
	 */
	void validateAuthToken(String token, String parentMethod, String errorCodePrefix);
	
	
	/**
	 * 
	 * @param emrIdStr
	 * @param errorCodePrefix
	 */
	void validateEMRId(String emrIdStr, String errorCodePrefix);
}
