package com.example.crudexample.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;

import com.example.crudexample.exception.helper.ExceptionDetail;
import com.example.crudexample.utils.constant.AppConstant;
import com.example.crudexample.utils.log.AppLog;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtil {
	private AppUtil() {
	}
	
	/**
	 * 
	 * @param object
	 * @return Json object as string
	 */
	public static String convertObjectToJson(final Object object) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException exception) {
			return null;
		}
	}

	/**
	 * 
	 * @return String: current date
	 */
	public static String getCurrentDate() {
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT);
		String currentDate = dateFormat.format(today);

		return currentDate;
	}

	/**
	 * 
	 * @return String: current time
	 */
	public static String getCurrentTime() {
		Date today = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat(AppConstant.HR_24_TIME_FORMAT);

		return timeFormat.format(today);
	}

	/**
	 * 
	 * @return String: current time stamp
	 */
	public static String getCurrentTimestamp() {
		long systemTimeMillis = System.currentTimeMillis();
		long systemTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(systemTimeMillis);

		return Long.toString(systemTimeSeconds);
	}
	
	/**
	 * Prints detailed error log based on the information provided
	 * 
	 * @param exceptionDetail
	 * @param appLog
	 */
	public static void printErrorLog(final ExceptionDetail exceptionDetail, final AppLog appLog) {
		appLog.printLog(Level.ERROR, "printErrorLog",
				"\n" + "================== Error Log: " + exceptionDetail.getDate() + " " + exceptionDetail.getTime()
						+ " ================== \n" + "GROUP NAME: " + exceptionDetail.getGroupName() + "\n"
						+ "API NAME: " + exceptionDetail.getApiName() + "\n" + "USER ID: " + exceptionDetail.getUserId()
						+ "\n" + "USER NAME: " + exceptionDetail.getUserName() + "\n" + "IMPACTED USER ID: "
						+ exceptionDetail.getImpactedUserId() + "\n" + "CODE: " + exceptionDetail.getFinalErrorCode()
						+ "\n" + "MESSAGE: " + exceptionDetail.getMessage() + "\n" + "CAUSE: "
						+ exceptionDetail.getCause() + "\n" + "CAUSE DESCRIPTION: " + exceptionDetail.getCauseDesc()
						+ "\n" + "REASON: " + exceptionDetail.getReason() + "\n" + "DESCRIPTION: "
						+ exceptionDetail.getReasonDesc() + "\n"
						+ "=========================== End Error Log ===========================");
	}
}
