package com.interland.training.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
		EmployeeException employeeException = new EmployeeException(employeeNotFoundException.getMessage(),
				employeeNotFoundException.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(employeeException, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmployeeCreateException.class)
	public ResponseEntity<Object> handleEmployeeCreateException(EmployeeCreateException employeeCreateException) {
		EmployeeException employeeException = new EmployeeException(employeeCreateException.getMessage(),
				employeeCreateException.getCause(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(employeeException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> validation(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		return new ResponseEntity<Object>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_GATEWAY);
	}

	private Map<String, List<String>> getErrorsMap(List<String> errors) {
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return errorResponse;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex){
		return new ResponseEntity<>("HTTP method not supported: " + ex.getMethod(), HttpStatus.METHOD_NOT_ALLOWED);
	}

	
}
