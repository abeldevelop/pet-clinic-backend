package com.abeldevelop.petclinic.services.customers.util;

import org.slf4j.Logger;

public class LoggerUtils {

	public static void trace(Logger logger, String message) {
		if(logger.isTraceEnabled()) {
			logger.trace(message);
		}
	}
	
	public static void trace(Logger logger, String format, Object... arguments) {
		if(logger.isTraceEnabled()) {
			logger.trace(format, arguments);
		}
	}
	
	public static void debug(Logger logger, String message) {
		if(logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}
	
	public static void debug(Logger logger, String format, Object... arguments) {
		if(logger.isDebugEnabled()) {
			logger.debug(format, arguments);
		}
	}
	
	public static void info(Logger logger, String message) {
		if(logger.isInfoEnabled()) {
			logger.info(message);
		}
	}
	
	public static void info(Logger logger, String format, Object... arguments) {
		if(logger.isInfoEnabled()) {
			logger.info(format, arguments);
		}
	}
	
	public static void warn(Logger logger, String message) {
		if(logger.isWarnEnabled()) {
			logger.warn(message);
		}
	}
	
	public static void warn(Logger logger, String format, Object... arguments) {
		if(logger.isWarnEnabled()) {
			logger.warn(format, arguments);
		}
	}
	
	public static void error(Logger logger, String message) {
		if(logger.isErrorEnabled()) {
			logger.error(message);
		}
	}
	
	public static void error(Logger logger, String format, Object... arguments) {
		if(logger.isErrorEnabled()) {
			logger.error(format, arguments);
		}
	}
}
