package com.abeldevelop.petclinic.services.vets.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abeldevelop.petclinic.services.vets.generated.resource.ErrorResponseResource;
import com.abeldevelop.petclinic.services.vets.objectmother.ExceptionObjectMother;

public class CustomResponseEntityExceptionHandlerTest {

	private CustomResponseEntityExceptionHandler customResponseEntityExceptionHandler;
	
	@BeforeEach
	public void setUp() {
		customResponseEntityExceptionHandler = new CustomResponseEntityExceptionHandler();
	}
	
	@Test
	public void testHandleNotFoundException() {
		//given
		NotFoundException notFoundException = ExceptionObjectMother.generateNotFoundException();
		
		//when
		ResponseEntity<Object> responseEntity = customResponseEntityExceptionHandler.handleNotFoundException(notFoundException);
		
		//then
		assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
		assertEquals(notFoundException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
	}
	
	@Test
	public void testHandleBadRequestException() {
		//given
		BadRequestException badRequestException = ExceptionObjectMother.generateBadRequestException();
		
		//when
		ResponseEntity<Object> responseEntity = customResponseEntityExceptionHandler.handleBadRequestException(badRequestException);
		
		//then
		assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
		assertEquals(badRequestException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
	}
	
	@Test
	public void testHandleRequestValidationException() {
		//given
		RequestValidationException requestValidationException = ExceptionObjectMother.generateRequestValidationException();
		
		//when
		ResponseEntity<Object> responseEntity = customResponseEntityExceptionHandler.handleRequestValidationException(requestValidationException);
		
		//then
		assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
		assertEquals(requestValidationException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
	}
	
	@Test
	public void testHandleInternalServerErrorException() {
		//given
		InternalServerErrorException internalServerErrorException = ExceptionObjectMother.generateInternalServerErrorException();
		
		//when
		ResponseEntity<Object> responseEntity = customResponseEntityExceptionHandler.handleInternalServerErrorException(internalServerErrorException);
		
		//then
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
		assertEquals(internalServerErrorException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
	}
}
