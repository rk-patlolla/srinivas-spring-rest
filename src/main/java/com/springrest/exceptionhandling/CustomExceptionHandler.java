package com.springrest.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice  
public class CustomExceptionHandler {

/*	@ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
	public ResponseEntity<UserErrorResponse> handleUserDetailsExistException(Exception ex) {
		UserErrorResponse errorResponse = new UserErrorResponse();
		errorResponse.setErrorCode(HttpStatus.CONFLICT.value());
		errorResponse.setExceptionMsg(ex.getMessage());
		return new ResponseEntity<UserErrorResponse>(errorResponse, HttpStatus.OK);
	}*/
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserErrorResponse> handleUserNotFoundException(Exception ex) {
		UserErrorResponse errorResponse = new UserErrorResponse();
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setExceptionMsg(ex.getMessage());
		return new ResponseEntity<UserErrorResponse>(errorResponse, HttpStatus.OK);
	}
	@ExceptionHandler(GroupNotFoundException.class)
	public ResponseEntity<UserErrorResponse> handleGroupNotFoundException(Exception ex) {
		UserErrorResponse errorResponse = new UserErrorResponse();
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setExceptionMsg(ex.getMessage());
		return new ResponseEntity<UserErrorResponse>(errorResponse, HttpStatus.OK);
	}

	
}
