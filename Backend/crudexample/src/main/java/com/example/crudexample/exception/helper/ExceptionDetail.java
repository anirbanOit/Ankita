package com.example.crudexample.exception.helper;

import com.example.crudexample.utils.APIGroupCode;
import com.example.crudexample.utils.AppUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionDetail {
	@JsonProperty("group_name")
	private String groupName;

	@JsonProperty("api_name")
	private String apiName;

	@JsonProperty("user_id")
	private long userId;

	@JsonProperty("user_name")
	private String userName;

	@JsonProperty("impacted_user_id")
	private long impactedUserId;

	@JsonIgnore
	private String errorCodePrefix;

	@JsonProperty("error_code")
	private String finalErrorCode;

	@JsonIgnore
	private ResponseCode responseCode;

	@JsonIgnore
	private ErrorCause errorCause;

	@JsonProperty("error_message")
	private String message;

	@JsonProperty("error_cause")
	private String cause;

	@JsonProperty("cause_desc")
	private String causeDesc;

	@JsonProperty("error_reason")
	private String reason;

	@JsonProperty("reason_desc")
	private String reasonDesc;

	@JsonProperty("error_date")
	private String date;

	@JsonProperty("error_time")
	private String time;

	/**
	 * 
	 */
	public ExceptionDetail() {
	}

	/**
	 * Use this constructor when error cause is known
	 * 
	 * @param userId
	 * @param userName
	 * @param impactedUserId
	 * @param errorCodePrefix
	 * @param responseCode
	 * @param errorCause
	 */
	public ExceptionDetail(final long userId, final String userName, final long impactedUserId,
			final String errorCodePrefix, final ResponseCode responseCode, final ErrorCause errorCause) {
		this.userId = userId;
		this.userName = userName;
		this.impactedUserId = impactedUserId;
		this.errorCodePrefix = errorCodePrefix;
		this.responseCode = responseCode;
		this.errorCause = errorCause;

		APIGroupCode apiGroupCode = APIGroupCode.findByErrorCode(this.errorCodePrefix);

		if (apiGroupCode != null) {
			this.groupName = apiGroupCode.getGroupName();
			this.apiName = apiGroupCode.getApiName();
		} else {
			this.groupName = APIGroupCode.GENERIC_BUSINESS.getGroupName();
			this.apiName = APIGroupCode.GENERIC_BUSINESS.getApiName();
		}

		if (errorCause != null) {
			this.cause = errorCause.getMessage();
			this.causeDesc = errorCause.getDesc();
		} else {
			this.cause = ErrorCause.NA.getMessage();
			this.causeDesc = ErrorCause.NA.getDesc();
		}

		if (responseCode != null) {
			this.finalErrorCode = errorCodePrefix + responseCode.getCode();
			this.message = responseCode.getMessage();
			this.reason = responseCode.getReason();
			this.reasonDesc = responseCode.getDesc();
		} else {
			this.finalErrorCode = errorCodePrefix + ResponseCode.UNCAUGHT.getCode();
			this.message = ResponseCode.UNCAUGHT.getMessage();
			this.reason = ResponseCode.UNCAUGHT.getReason();
			this.reasonDesc = ResponseCode.UNCAUGHT.getDesc();
		}

		this.date = AppUtil.getCurrentDate();
		this.time = AppUtil.getCurrentTime();
	}

	/**
	 * Use this constructor when error cause is unknown
	 * 
	 * @param userId
	 * @param userName
	 * @param impactedUserId
	 * @param errorCodePrefix
	 * @param responseCode
	 * @param cause
	 */
	public ExceptionDetail(final long userId, final String userName, final long impactedUserId,
			final String errorCodePrefix, final ResponseCode responseCode, final String cause) {
		this.userId = userId;
		this.userName = userName;
		this.impactedUserId = impactedUserId;
		this.errorCodePrefix = errorCodePrefix;
		this.responseCode = responseCode;
		this.errorCause = ErrorCause.NA;

		APIGroupCode apiGroupCode = APIGroupCode.findByErrorCode(this.errorCodePrefix);

		if (apiGroupCode != null) {
			this.groupName = apiGroupCode.getGroupName();
			this.apiName = apiGroupCode.getApiName();
		} else {
			this.groupName = APIGroupCode.GENERIC_BUSINESS.getGroupName();
			this.apiName = APIGroupCode.GENERIC_BUSINESS.getApiName();
		}

		this.cause = errorCause.getMessage();
		this.causeDesc = cause;

		if (responseCode != null) {
			this.finalErrorCode = errorCodePrefix + responseCode.getCode();
			this.message = responseCode.getMessage();
			this.reason = responseCode.getReason();
			this.reasonDesc = responseCode.getDesc();
		} else {
			this.finalErrorCode = errorCodePrefix + ResponseCode.UNCAUGHT.getCode();
			this.message = ResponseCode.UNCAUGHT.getMessage();
			this.reason = ResponseCode.UNCAUGHT.getReason();
			this.reasonDesc = ResponseCode.UNCAUGHT.getDesc();
		}

		this.date = AppUtil.getCurrentDate();
		this.time = AppUtil.getCurrentTime();
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @return the apiName
	 */
	public String getApiName() {
		return apiName;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the impactedUserId
	 */
	public long getImpactedUserId() {
		return impactedUserId;
	}

	/**
	 * @return the errorCodePrefix
	 */
	@JsonIgnore
	public String getErrorCodePrefix() {
		return errorCodePrefix;
	}

	/**
	 * @return the finalErrorCode
	 */
	public String getFinalErrorCode() {
		return finalErrorCode;
	}

	/**
	 * @return the responseCode
	 */
	@JsonIgnore
	public ResponseCode getResponseCode() {
		return responseCode;
	}

	/**
	 * @return the errorCause
	 */
	@JsonIgnore
	public ErrorCause getErrorCause() {
		return errorCause;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the cause
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * @return the causeDesc
	 */
	public String getCauseDesc() {
		return causeDesc;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @return the reasonDesc
	 */
	public String getReasonDesc() {
		return reasonDesc;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @param apiName
	 *            the apiName to set
	 */
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param impactedUserId
	 *            the impactedUserId to set
	 */
	public void setImpactedUserId(long impactedUserId) {
		this.impactedUserId = impactedUserId;
	}

	/**
	 * @param errorCodePrefix
	 *            the errorCodePrefix to set
	 */
	public void setErrorCodePrefix(String errorCodePrefix) {
		this.errorCodePrefix = errorCodePrefix;
	}

	/**
	 * @param finalErrorCode
	 *            the finalErrorCode to set
	 */
	public void setFinalErrorCode(String finalErrorCode) {
		this.finalErrorCode = finalErrorCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @param errorCause
	 *            the errorCause to set
	 */
	public void setErrorCause(ErrorCause errorCause) {
		this.errorCause = errorCause;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * @param causeDesc
	 *            the causeDesc to set
	 */
	public void setCauseDesc(String causeDesc) {
		this.causeDesc = causeDesc;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @param reasonDesc
	 *            the reasonDesc to set
	 */
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
