package com.abeldevelop.petclinic.services.vets.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -5805957618323808837L;

	public BadRequestException(String message) {
		super(message);
	}
	
}
