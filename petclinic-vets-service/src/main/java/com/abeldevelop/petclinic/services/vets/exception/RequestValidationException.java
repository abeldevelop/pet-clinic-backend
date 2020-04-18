package com.abeldevelop.petclinic.services.vets.exception;

public class RequestValidationException extends RuntimeException {

	private static final long serialVersionUID = -5805957618323808837L;

	public RequestValidationException(String message) {
		super(message);
	}
	
}