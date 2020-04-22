package com.abeldevelop.petclinic.library.common.util;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerUtilsTest {

	@Test
	public void testTrace() {
		changeLogLevel(Level.TRACE);
		LoggerUtils.trace(log, "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.trace(log, "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testTraceWithArguments() {
		changeLogLevel(Level.TRACE);
		LoggerUtils.trace(log, "message {}", "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.trace(log, "message {}", "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testDebug() {
		changeLogLevel(Level.DEBUG);
		LoggerUtils.debug(log, "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.debug(log, "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testDebugWithArguments() {
		changeLogLevel(Level.DEBUG);
		LoggerUtils.debug(log, "message {}", "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.debug(log, "message {}", "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testInfo() {
		changeLogLevel(Level.INFO);
		LoggerUtils.info(log, "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.info(log, "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testInfoWithArguments() {
		changeLogLevel(Level.INFO);
		LoggerUtils.info(log, "message {}", "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.info(log, "message {}", "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testWarn() {
		changeLogLevel(Level.WARN);
		LoggerUtils.warn(log, "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.warn(log, "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testWarnWithArguments() {
		changeLogLevel(Level.WARN);
		LoggerUtils.warn(log, "message {}", "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.warn(log, "message {}", "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testError() {
		changeLogLevel(Level.ERROR);
		LoggerUtils.error(log, "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.error(log, "message");
		changeLogLevel(Level.WARN);
	}
	
	@Test
	public void testErrorWithArguments() {
		changeLogLevel(Level.ERROR);
		LoggerUtils.error(log, "message {}", "message");
		changeLogLevel(Level.OFF);
		LoggerUtils.error(log, "message {}", "message");
		changeLogLevel(Level.WARN);
	}
	
	private void changeLogLevel(Level level) {
		((ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(level);
	}
}
