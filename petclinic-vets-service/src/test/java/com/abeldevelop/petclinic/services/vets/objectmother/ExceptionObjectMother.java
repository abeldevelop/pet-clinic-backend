package com.abeldevelop.petclinic.services.vets.objectmother;

import com.abeldevelop.petclinic.services.vets.exception.BadRequestException;
import com.abeldevelop.petclinic.services.vets.exception.InternalServerErrorException;
import com.abeldevelop.petclinic.services.vets.exception.NotFoundException;
import com.abeldevelop.petclinic.services.vets.exception.RequestValidationException;

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
