package com.abeldevelop.petclinic.library.common.exception;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = -5805957618323808837L;

	public InternalServerErrorException(String message) {
		super(message);
	}
	
}