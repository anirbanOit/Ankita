package com.example.crudexample.utils.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class AppLog {
	private Logger logger;	
	private static Class<?> appClass;
	private static AppLog instance;
	
	/**
	 * 
	 * @param appClass
	 */
	private AppLog(){
		logger = Logger.getLogger(appClass.getSimpleName());
	}
	
	public static AppLog getInstance(final Class<?> projClass){
		if(instance == null || !appClass.getSimpleName().equalsIgnoreCase(projClass.getSimpleName())){
			appClass = projClass;
			instance = new AppLog();
		}
		
		return instance;
	}
	
	/**
	 * 
	 * @param level
	 * @param methodName
	 * @param logMessage
	 */
	public void printLog(final Level level, final String methodName, final String logMessage){
		String logMessageBody = methodName + "() ==> " + logMessage;
		
		logger.log(level, logMessageBody);
	}
	
	/**
	 * 
	 * @param level
	 * @param methodName
	 * @param object
	 */
	public void printObjectLog(final Level level, final String methodName, final Object object) {
		String logMessageBody = methodName + "() ==> " + object.toString();
		
		logger.log(level, logMessageBody);
	}
}
