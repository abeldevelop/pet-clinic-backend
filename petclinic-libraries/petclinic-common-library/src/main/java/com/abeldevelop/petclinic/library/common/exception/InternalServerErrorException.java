package com.abeldevelop.petclinic.library.common.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends PetClinicException {

	private static final long serialVersionUID = -5805957618323808837L;

	private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public InternalServerErrorException(int code, String message) {
		super(code, message, STATUS);
	}

	public InternalServerErrorException(int code, String message, Object... arguments) {
		super(code, message, STATUS, arguments);
	}
	
	public InternalServerErrorException(int code, String message, Throwable cause) {
		super(code, message, STATUS, cause);
	}
	
	public InternalServerErrorException(int code, String message, Throwable cause, Object... arguments) {
		super(code, message, STATUS, cause, arguments);
	}
	
}
