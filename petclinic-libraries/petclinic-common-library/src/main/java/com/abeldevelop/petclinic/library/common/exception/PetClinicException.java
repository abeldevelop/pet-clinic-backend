package com.abeldevelop.petclinic.library.common.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PetClinicException extends RuntimeException {

	private static final long serialVersionUID = -901056780222433834L;

	private final int code;
	
	private final HttpStatus status;
	
	private final List<Object> arguments;
	
	protected PetClinicException(int code, String message, HttpStatus status) {
		super(message);
		this.code = code;
		this.status = status;
		this.arguments = null;
	}

	protected PetClinicException(int code, String message, HttpStatus status, Object... arguments) {
		super(message);
		this.code = code;
		this.status = status;
		this.arguments = Arrays.asList(arguments);
	}
	
	protected PetClinicException(int code, String message, HttpStatus status, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.status = status;
		this.arguments = null;
	}
	
	protected PetClinicException(int code, String message, HttpStatus status, Throwable cause, Object... arguments) {
		super(message, cause);
		this.code = code;
		this.status = status;
		this.arguments = Arrays.asList(arguments);
	}

}
