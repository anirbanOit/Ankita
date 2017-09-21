package com.example.crudexample.exception.helper;

import java.util.Locale;

import org.springframework.validation.Errors;

import com.example.crudexample.exception.BadRequestException;
import com.example.crudexample.utils.APIGroupCode;
import com.example.crudexample.utils.constant.AppConstant;

public class ValidationErrorBuilder {
	private ValidationErrorBuilder() {
	}

	public static BadRequestException fromBindingErrors(final Errors errors) {
		String errorCodePrefix = APIGroupCode.GENERIC_BUSINESS.getGroupCode()
				+ APIGroupCode.GENERIC_BUSINESS.getApiCode() + AppConstant.API_CODE_ERROR;
		String errorCode = "UNCAUGHT";
		ErrorCause errorCause = ErrorCause.NA;

		String errorObjMsg = errors.getAllErrors().get(0).getDefaultMessage();
		String[] messageParts;

		if (errorObjMsg.contains(AppConstant.ANNOTATION_MESSAGE_DELIMITER)) {
			messageParts = errorObjMsg.split(AppConstant.ANNOTATION_MESSAGE_DELIMITER);
		} else {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix,
					ResponseCode.valueOf(errorCode.toUpperCase(Locale.ENGLISH)), errorCause);
			return new BadRequestException(exceptionDetail);
		}

		if (messageParts.length == 1) {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix,
					ResponseCode.valueOf(errorCode.toUpperCase(Locale.ENGLISH)), errorCause);
			return new BadRequestException(exceptionDetail);
		}

		if (messageParts.length == 2) {
			errorCodePrefix = getErrorStatusCode(messageParts);
			errorCode = getErrorCodeMsg(messageParts);
		} else if (messageParts.length == 3) {
			errorCodePrefix = getErrorStatusCode(messageParts);
			errorCode = getErrorCodeMsg(messageParts);
			errorCause = ErrorCause.findByMessage(messageParts[2]);
		}

		ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix,
				ResponseCode.valueOf(errorCode.toUpperCase(Locale.ENGLISH)), errorCause);

		return new BadRequestException(exceptionDetail);
	}

	public static String getErrorStatusCode(String[] parts) {
		String API_GROUP_CODE = parts[0];

		return APIGroupCode.valueOf(API_GROUP_CODE).getGroupCode() + APIGroupCode.valueOf(API_GROUP_CODE).getApiCode()
				+ AppConstant.API_CODE_ERROR;
	}

	public static String getErrorCodeMsg(String[] parts) {
		return parts[1];
	}
}
