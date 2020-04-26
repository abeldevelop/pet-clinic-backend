package com.abeldevelop.petclinic.library.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends PetClinicException {

	private static final long serialVersionUID = -5805957618323808837L;

	private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
	
	public BadRequestException(int code, String message) {
		super(code, message, STATUS);
	}

	public BadRequestException(int code, String message, Object... arguments) {
		super(code, message, STATUS, arguments);
	}
	
	public BadRequestException(int code, String message, Throwable cause) {
		super(code, message, STATUS, cause);
	}
	
	public BadRequestException(int code, String message, Throwable cause, Object... arguments) {
		super(code, message, STATUS, cause, arguments);
	}
	
}
