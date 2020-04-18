package com.abeldevelop.petclinic.services.vets.util;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerUtilsTest {

	@Test
	public void testTrace() {
		LoggerUtils.trace(log, "message");
	}
	
	@Test
	public void testTraceWithArguments() {
		LoggerUtils.trace(log, "message {}", "message");
	}
	
	@Test
	public void testDebug() {
		LoggerUtils.debug(log, "message");
	}
	
	@Test
	public void testDebugWithArguments() {
		LoggerUtils.debug(log, "message {}", "message");
	}
	
	@Test
	public void testInfo() {
		LoggerUtils.info(log, "message");
	}
	
	@Test
	public void testInfoWithArguments() {
		LoggerUtils.info(log, "message {}", "message");
	}
	
	@Test
	public void testWarn() {
		LoggerUtils.warn(log, "message");
	}
	
	@Test
	public void testWarnWithArguments() {
		LoggerUtils.warn(log, "message {}", "message");
	}
	
	public void error() {
		LoggerUtils.error(log, "message");
	}
	
	public void errorWithArguments() {
		LoggerUtils.error(log, "message {}", "message");
	}
}
