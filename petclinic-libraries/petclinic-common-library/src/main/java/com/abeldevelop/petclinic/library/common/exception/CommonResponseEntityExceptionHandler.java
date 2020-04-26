package com.abeldevelop.petclinic.library.common.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.abeldevelop.petclinic.library.common.component.MessageFormatter;
import com.abeldevelop.petclinic.library.common.resources.ErrorResponseResource;
import com.abeldevelop.petclinic.library.common.util.LoggerUtils;

import brave.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
@RestController
public class CommonResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageFormatter messageFormatter;
	private final Tracer tracer;

	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleHttpRequestMethodNotSupported");
		ResponseEntity<Object> response = super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleHttpMediaTypeNotSupported");
		ResponseEntity<Object> response = super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleHttpMediaTypeNotAcceptable");
		ResponseEntity<Object> response = super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleMissingPathVariable");
		ResponseEntity<Object> response = super.handleMissingPathVariable(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleMissingServletRequestParameter");
		ResponseEntity<Object> response = super.handleMissingServletRequestParameter(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleServletRequestBindingException");
		ResponseEntity<Object> response = super.handleServletRequestBindingException(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleConversionNotSupported");
		ResponseEntity<Object> response = super.handleConversionNotSupported(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleTypeMismatch");
		ResponseEntity<Object> response = super.handleTypeMismatch(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleHttpMessageNotReadable");
		ResponseEntity<Object> response = super.handleHttpMessageNotReadable(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleHttpMessageNotWritable");
		ResponseEntity<Object> response = super.handleHttpMessageNotWritable(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleMethodArgumentNotValid");
		ResponseEntity<Object> response = super.handleMethodArgumentNotValid(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleMissingServletRequestPart");
		ResponseEntity<Object> response = super.handleMissingServletRequestPart(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleBindException");
		ResponseEntity<Object> response = super.handleBindException(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleNoHandlerFoundException");
		ResponseEntity<Object> response = super.handleNoHandlerFoundException(ex, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		LoggerUtils.error(log, "handleAsyncRequestTimeoutException");
		ResponseEntity<Object> response = super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		LoggerUtils.error(log, "handleExceptionInternal");
		ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, status, request);
		return new ResponseEntity<>(generateErrorResponseResource(ex), response.getHeaders(), response.getStatusCode());
	}
	
	@ExceptionHandler(PetClinicException.class)
	public final ResponseEntity<Object> handlePetClinicException(PetClinicException ex) {
		if(ex.getStatus().is5xxServerError()) {
			LoggerUtils.error(log, ex.getMessage());
		} else {
			LoggerUtils.warn(log, ex.getMessage());
		}
		
		return new ResponseEntity<>(generateErrorResponseResource(ex), ex.getStatus());
	}
	
	private ErrorResponseResource generateErrorResponseResource(Exception ex) {
		String message = ex.getMessage();
		Integer code = null;
		String id = null;
		if(ex instanceof PetClinicException) {
			message = messageFormatter(ex.getMessage(), ((PetClinicException)ex).getArguments());
			code = ((PetClinicException)ex).getCode();
		}
		
		if(tracer.currentSpan() != null && tracer.currentSpan().context() != null) {
			id = tracer.currentSpan().context().traceIdString();
		}
		
		return ErrorResponseResource.builder()
				.timestamp(LocalDateTime.now())
				.id(id)
				.code(code)
				.message(message)
				.build();
	}
	
	private String messageFormatter(String message, List<Object> arguments) {
		if(CollectionUtils.isEmpty(arguments)) {
			return message;
		}
		return messageFormatter.format(message, arguments);
	}
	
}
