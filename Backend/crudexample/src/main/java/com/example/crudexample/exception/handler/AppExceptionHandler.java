package com.example.crudexample.exception.handler;

import org.apache.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.api.message.response.GenericErrorResponse;
import com.example.crudexample.exception.BadRequestException;
import com.example.crudexample.exception.base.AppException;
import com.example.crudexample.exception.helper.ExceptionDetail;
import com.example.crudexample.exception.helper.ResponseCode;
import com.example.crudexample.exception.helper.ValidationErrorBuilder;
import com.example.crudexample.utils.APIGroupCode;
import com.example.crudexample.utils.AppUtil;
import com.example.crudexample.utils.constant.AppConstant;
import com.example.crudexample.utils.log.AppLog;

@ControllerAdvice(annotations = RestController.class)
public class AppExceptionHandler {
	private final AppLog appLog = AppLog.getInstance(AppExceptionHandler.class);
	private final String RESPONSE_PREFIX = "Response >> ";

	@ExceptionHandler(value = AppException.class)
	public ResponseEntity<AbstractResponse> handleApiException(final AppException wiPhyException) {
		ExceptionDetail exceptionDetail = wiPhyException.getExceptionDetail();
		int httpStatusCode = wiPhyException.getHttpStatusCode();

		AppUtil.printErrorLog(exceptionDetail, appLog);

		GenericErrorResponse genericErrorResponse = new GenericErrorResponse(exceptionDetail, httpStatusCode);

		appLog.printLog(Level.DEBUG, "handleApiException",
				RESPONSE_PREFIX + AppUtil.convertObjectToJson(genericErrorResponse));

		return ResponseEntity.status(AppConstant.APP_HTTP_ERROR_CODE.get(httpStatusCode)).body(genericErrorResponse);
	}

	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<AbstractResponse> handleMissingServletRequestParameterException(final Exception exception) {
		String errorCodePrefix = APIGroupCode.GENERIC_BUSINESS.getGroupCode()
				+ APIGroupCode.GENERIC_BUSINESS.getApiCode() + AppConstant.API_CODE_ERROR;

		ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.INVALID_VALUE,
				exception.getMessage());

		AppUtil.printErrorLog(exceptionDetail, appLog);

		GenericErrorResponse genericErrorResponse = new GenericErrorResponse(exceptionDetail,
				AppConstant.HTTP_STATUS_BAD_REQUEST);

		appLog.printLog(Level.DEBUG, "handleMissingServletRequestParameterException",
				RESPONSE_PREFIX + AppUtil.convertObjectToJson(genericErrorResponse));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericErrorResponse);
	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<AbstractResponse> handleMethodArgumentTypeMismatchException(final Exception exception) {
		String errorCodePrefix = APIGroupCode.GENERIC_BUSINESS.getGroupCode()
				+ APIGroupCode.GENERIC_BUSINESS.getApiCode() + AppConstant.API_CODE_ERROR;

		ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.INVALID_VALUE,
				exception.getMessage());

		AppUtil.printErrorLog(exceptionDetail, appLog);

		GenericErrorResponse genericErrorResponse = new GenericErrorResponse(exceptionDetail,
				AppConstant.HTTP_STATUS_BAD_REQUEST);

		appLog.printLog(Level.DEBUG, "handleMethodArgumentTypeMismatchException",
				RESPONSE_PREFIX + AppUtil.convertObjectToJson(genericErrorResponse));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericErrorResponse);
	}

	@ExceptionHandler(value = ServletRequestBindingException.class)
	public ResponseEntity<AbstractResponse> handleServletRequestBindingException(final Exception exception) {
		String errorCodePrefix = APIGroupCode.GENERIC_TECHNICAL.getGroupCode()
				+ APIGroupCode.GENERIC_TECHNICAL.getApiCode() + AppConstant.API_CODE_ERROR;

		ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix,
				ResponseCode.MANDATORY_PARAMETER, exception.getMessage());

		AppUtil.printErrorLog(exceptionDetail, appLog);

		GenericErrorResponse genericErrorResponse = new GenericErrorResponse(exceptionDetail,
				AppConstant.HTTP_STATUS_BAD_REQUEST);

		appLog.printLog(Level.DEBUG, "handleServletRequestBindingException",
				RESPONSE_PREFIX + AppUtil.convertObjectToJson(genericErrorResponse));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericErrorResponse);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<AbstractResponse> handleMethodArgumentNotValidException(final Exception exception) {
		BadRequestException error = createValidationError(exception);

		int httpStatusCode = error.getHttpStatusCode();

		ExceptionDetail exceptionDetail = error.getExceptionDetail();

		AppUtil.printErrorLog(exceptionDetail, appLog);

		GenericErrorResponse genericErrorResponse = new GenericErrorResponse(exceptionDetail, httpStatusCode);

		appLog.printLog(Level.DEBUG, "handleMethodArgumentNotValidException",
				RESPONSE_PREFIX + AppUtil.convertObjectToJson(genericErrorResponse));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericErrorResponse);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<AbstractResponse> handleIllegalArgumentException(final Exception exception) {
		BadRequestException error = createValidationError(exception);

		int httpStatusCode = error.getHttpStatusCode();

		ExceptionDetail exceptionDetail = error.getExceptionDetail();

		AppUtil.printErrorLog(exceptionDetail, appLog);

		GenericErrorResponse genericErrorResponse = new GenericErrorResponse(exceptionDetail, httpStatusCode);

		appLog.printLog(Level.DEBUG, "handleIllegalArgumentException",
				RESPONSE_PREFIX + AppUtil.convertObjectToJson(genericErrorResponse));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericErrorResponse);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<AbstractResponse> handleException(final Exception exception) {
		String errorCodePrefix = APIGroupCode.GENERIC_TECHNICAL.getGroupCode()
				+ APIGroupCode.GENERIC_TECHNICAL.getApiCode() + AppConstant.API_CODE_ERROR;

		ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.UNCAUGHT,
				exception.getMessage());

		AppUtil.printErrorLog(exceptionDetail, appLog);

		GenericErrorResponse genericErrorResponse = new GenericErrorResponse(exceptionDetail,
				AppConstant.HTTP_STATUS_INTERNAL_SERVER_ERROR);

		appLog.printLog(Level.DEBUG, "handleException",
				RESPONSE_PREFIX + AppUtil.convertObjectToJson(genericErrorResponse));

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericErrorResponse);
	}

	/**
	 * 
	 * @param exception
	 * @return BadRequestException
	 */
	private BadRequestException createValidationError(final Exception exception) {
		return ValidationErrorBuilder
				.fromBindingErrors(((MethodArgumentNotValidException) exception).getBindingResult());
	}
}
