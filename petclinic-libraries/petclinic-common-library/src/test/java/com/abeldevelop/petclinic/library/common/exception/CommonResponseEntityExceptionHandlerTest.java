package com.abeldevelop.petclinic.library.common.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abeldevelop.petclinic.library.common.component.MessageFormatter;
import com.abeldevelop.petclinic.library.common.objectmother.ExceptionObjectMother;
import com.abeldevelop.petclinic.library.common.resources.ErrorResponseResource;

//@ExtendWith(MockitoExtension.class)
public class CommonResponseEntityExceptionHandlerTest {
//
//	private CommonResponseEntityExceptionHandler commonResponseEntityExceptionHandler;
//
//	@Mock
//	private MessageFormatter messageFormatter;
//	
//	@BeforeEach
//	public void setUp() {
//		commonResponseEntityExceptionHandler = new CommonResponseEntityExceptionHandler(messageFormatter);
//	}
//	
//	@Test
//	public void testHandleNotFoundException() {
//		//given
//		NotFoundException notFoundException = ExceptionObjectMother.generateNotFoundException();
//		
//		//when
//		ResponseEntity<Object> responseEntity = commonResponseEntityExceptionHandler.handlePetClinicException(notFoundException);
//		
//		//then
//		assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCodeValue());
//		assertEquals(notFoundException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
//	}
//	
//	@Test
//	public void testHandleBadRequestException() {
//		//given
//		BadRequestException badRequestException = ExceptionObjectMother.generateBadRequestException();
//		
//		//when
//		ResponseEntity<Object> responseEntity = commonResponseEntityExceptionHandler.handlePetClinicException(badRequestException);
//		
//		//then
//		assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
//		assertEquals(badRequestException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
//	}
//	
//	@Test
//	public void testHandleInternalServerErrorException() {
//		//given
//		InternalServerErrorException internalServerErrorException = ExceptionObjectMother.generateInternalServerErrorException();
//		
//		//when
//		ResponseEntity<Object> responseEntity = commonResponseEntityExceptionHandler.handlePetClinicException(internalServerErrorException);
//		
//		//then
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseEntity.getStatusCodeValue());
//		assertEquals(internalServerErrorException.getMessage(), ((ErrorResponseResource)responseEntity.getBody()).getMessage());
//	}
}
