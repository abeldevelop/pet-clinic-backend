package com.abeldevelop.petclinic.services.customers.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.abeldevelop.petclinic.services.customers.generated.resource.ErrorResponseResource;
import com.abeldevelop.petclinic.services.customers.util.LoggerUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		LoggerUtils.warn(log, ex.getMessage());
		return new ResponseEntity<>(ErrorResponseResource.builder()
				.timestamp(LocalDateTime.now())
				.id(null)
				.code(null)
				.message(ex.getMessage())
				.build(), 
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
		LoggerUtils.warn(log, ex.getMessage());
		return new ResponseEntity<>(ErrorResponseResource.builder()
				.timestamp(LocalDateTime.now())
				.id(null)
				.code(null)
				.message(ex.getMessage())
				.build(), 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RequestValidationException.class)
	public final ResponseEntity<Object> handleRequestValidationException(RequestValidationException ex) {
		LoggerUtils.warn(log, ex.getMessage());
		return new ResponseEntity<>(ErrorResponseResource.builder()
				.timestamp(LocalDateTime.now())
				.id(null)
				.code(null)
				.message(ex.getMessage())
				.build(), 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InternalServerErrorException.class)
	public final ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException ex) {
		LoggerUtils.warn(log, ex.getMessage());
		return new ResponseEntity<>(ErrorResponseResource.builder()
				.timestamp(LocalDateTime.now())
				.id(null)
				.code(null)
				.message(ex.getMessage())
				.build(), 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
