package com.abeldevelop.petclinic.services.visits.objectmother;

import com.abeldevelop.petclinic.services.visits.exception.BadRequestException;
import com.abeldevelop.petclinic.services.visits.exception.InternalServerErrorException;
import com.abeldevelop.petclinic.services.visits.exception.NotFoundException;
import com.abeldevelop.petclinic.services.visits.exception.RequestValidationException;

public class ExceptionObjectMother {

	private ExceptionObjectMother() {
		
	}
	
	public static BadRequestException generateBadRequestException() {
		return new BadRequestException("BadRequestException");
	}
	
	public static InternalServerErrorException generateInternalServerErrorException() {
		return new InternalServerErrorException("InternalServerErrorException");
	}
	
	public static NotFoundException generateNotFoundException() {
		return new NotFoundException("NotFoundException");
	}
	
	public static RequestValidationException generateRequestValidationException() {
		return new RequestValidationException("RequestValidationException");
	}
	
}
