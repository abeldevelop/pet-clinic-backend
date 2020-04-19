package com.abeldevelop.petclinic.services.visits.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5805957618323808837L;

	public NotFoundException(String message) {
		super(message);
	}
	
}