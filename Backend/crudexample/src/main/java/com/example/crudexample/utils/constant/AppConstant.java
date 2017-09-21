package com.example.crudexample.utils.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.example.crudexample.utils.APIGroupCode;

public class AppConstant {

	public static final Map<Integer, HttpStatus> APP_HTTP_ERROR_CODE = new HashMap<>();

	public final static String REQUEST_HEADER_TOKEN = "session_token";
	public static String TOKEN = "AFO1Y6O44QVYRSNKY5WZHF813GHMCOH0BUV1CT1PB5H19598QD9GM0U60CMDVVDYWD6M22CI67MD9W9JSNEYGC00RLQY3OEMDPZFZK809EHPLK09YCP4X1P0KERM9WVTVOT7IM02WKKIJS0HS4NYSKAMIVMOI8J8OQQCRVHVYGUHC36RZODFR2C6H4MZO83MRU3JP01A";

	public static final String BAD_REQUEST_EXCEPTION = "badRequestException";
	public static final String SESSION_EXCEPTION = "sessionException";

	public static final long USER_ID_NONE = 0;
	public static final long IMPACTED_USER_ID_NONE = 0;
	public static final String USER_NAME_NONE = "";

	public static final String HR_24_TIME_FORMAT = "HH:mm:ss";
	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final String TIME_FORMAT = "hh:mm:ss a zzz";
	public static final String HOUR_ABBREVIATION_FORMAT = "hr";
	public static final String MINUTE_ABBREVIATION_FORMAT = "min";
	public static final String SECOND_ABBREVIATION_FORMAT = "sec";

	public static final String ALPHA_NUMERIC_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static final String ZERO_VALUE_STRING = "0";
	public static final String ID_DELIMITER = "_";
	public static final int ENTITY_ID_DELIMETER_SPLIT_LIMIT = 2;

	public static final String API_STATUS_OK = "ok";
	public static final String API_STATUS_FAILED = "failed";

	/* API CODE: Generic */
	public static final String API_CODE_ERROR = "00";
	public static final String API_CODE_SUCCESS = "01";

	public static final String ANNOTATION_MESSAGE_DELIMITER = "~";

	public static final String GENERIC_SUCCESS_CODE = APIGroupCode.GENERIC_SUCCESS.getGroupCode()
			+ APIGroupCode.GENERIC_SUCCESS.getApiCode() + API_CODE_SUCCESS + "000";

	public static final int HTTP_STATUS_BAD_REQUEST = 1;
	public static final int HTTP_STATUS_UNAUTHORIZED = 2;
	public static final int HTTP_STATUS_FORBIDDEN = 3;
	public static final int HTTP_STATUS_NOT_FOUND = 4;
	public static final int HTTP_STATUS_UNSUPPORTED_MEDIA_TYPE = 5;
	public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 6;

	private AppConstant() {
	}

	public static final void setHttpStatusCode() {
		APP_HTTP_ERROR_CODE.put(HTTP_STATUS_BAD_REQUEST, HttpStatus.BAD_REQUEST);
		APP_HTTP_ERROR_CODE.put(HTTP_STATUS_UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
		APP_HTTP_ERROR_CODE.put(HTTP_STATUS_FORBIDDEN, HttpStatus.FORBIDDEN);
		APP_HTTP_ERROR_CODE.put(HTTP_STATUS_NOT_FOUND, HttpStatus.NOT_FOUND);
		APP_HTTP_ERROR_CODE.put(HTTP_STATUS_UNSUPPORTED_MEDIA_TYPE, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		APP_HTTP_ERROR_CODE.put(HTTP_STATUS_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
