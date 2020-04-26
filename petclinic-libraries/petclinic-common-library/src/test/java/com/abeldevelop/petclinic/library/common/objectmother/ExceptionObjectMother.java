package com.abeldevelop.petclinic.library.common.objectmother;

import com.abeldevelop.petclinic.library.common.exception.BadRequestException;
import com.abeldevelop.petclinic.library.common.exception.InternalServerErrorException;
import com.abeldevelop.petclinic.library.common.exception.NotFoundException;

public class ExceptionObjectMother {

	private ExceptionObjectMother() {
		
	}
	
	public static BadRequestException generateBadRequestException() {
		return new BadRequestException(0, "BadRequestException");
	}
	
	public static InternalServerErrorException generateInternalServerErrorException() {
		return new InternalServerErrorException(0, "InternalServerErrorException");
	}
	
	public static NotFoundException generateNotFoundException() {
		return new NotFoundException(0, "NotFoundException");
	}
	
}
