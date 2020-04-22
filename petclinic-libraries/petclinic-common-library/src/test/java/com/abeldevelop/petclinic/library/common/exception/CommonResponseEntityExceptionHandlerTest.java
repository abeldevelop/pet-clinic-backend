package com.abeldevelop.petclinic.library.common.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abeldevelop.petclinic.library.common.objectmother.ExceptionObjectMother;
import com.abeldevelop.petclinic.library.common.resources.ErrorResponseResource;

public class CommonResponseEntityExceptionHandlerTest {

	private CommonResponseEntityExceptionHandler commonResponseEntityExceptionHandler;
	
	@BeforeEach
	public void setUp() {
		commonResponseEntityExceptionHandler = new CommonResponseEntityExceptionHandler();
	}
	
	@Test
	public void testHandleNotFoundException() {
		//given
		NotFoundException notFoundException = ExceptionObjectMother.generateNotFoundException();
		
		//when
		ResponseEntity<Object> responseEntity = commonResponseEntityExceptionHandler.handleNotFoundException(notFoundException);
		
		//then
		assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
		assertEquals(notFoundException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
	}
	
	@Test
	public void testHandleBadRequestException() {
		//given
		BadRequestException badRequestException = ExceptionObjectMother.generateBadRequestException();
		
		//when
		ResponseEntity<Object> responseEntity = commonResponseEntityExceptionHandler.handleBadRequestException(badRequestException);
		
		//then
		assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
		assertEquals(badRequestException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
	}
	
	@Test
	public void testHandleRequestValidationException() {
		//given
		RequestValidationException requestValidationException = ExceptionObjectMother.generateRequestValidationException();
		
		//when
		ResponseEntity<Object> responseEntity = commonResponseEntityExceptionHandler.handleRequestValidationException(requestValidationException);
		
		//then
		assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
		assertEquals(requestValidationException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
	}
	
	@Test
	public void testHandleInternalServerErrorException() {
		//given
		InternalServerErrorException internalServerErrorException = ExceptionObjectMother.generateInternalServerErrorException();
		
		//when
		ResponseEntity<Object> responseEntity = commonResponseEntityExceptionHandler.handleInternalServerErrorException(internalServerErrorException);
		
		//then
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
		assertEquals(internalServerErrorException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
	}
}
