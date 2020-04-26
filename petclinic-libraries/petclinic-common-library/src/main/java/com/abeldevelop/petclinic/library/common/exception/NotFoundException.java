package com.abeldevelop.petclinic.library.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends PetClinicException {

	private static final long serialVersionUID = -5805957618323808837L;

	private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
	
	public NotFoundException(int code, String message) {
		super(code, message, STATUS);
	}

	public NotFoundException(int code, String message, Object... arguments) {
		super(code, message, STATUS, arguments);
	}
	
	public NotFoundException(int code, String message, Throwable cause) {
		super(code, message, STATUS, cause);
	}
	
	public NotFoundException(int code, String message, Throwable cause, Object... arguments) {
		super(code, message, STATUS, cause, arguments);
	}
	
}
