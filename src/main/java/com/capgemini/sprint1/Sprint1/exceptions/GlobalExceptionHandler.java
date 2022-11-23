package com.capgemini.sprint1.Sprint1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = InvalidLoginCredentials.class)
	public ResponseEntity<String> invalidLoginCredentials(InvalidLoginCredentials e) {
		return new ResponseEntity<String>("Invalid Login Credentials",HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = ClientNotFoundException.class)
	public ResponseEntity<String> clientNotFoundException(ClientNotFoundException e) {
		return new ResponseEntity<String>("No Client found with given Id",HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = ProductAlreadyOwnedException.class)
	public ResponseEntity<String> productAlreadyBoughtException(ProductAlreadyOwnedException e) {
		return new ResponseEntity<String>("Product is already owned",HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = ProductOutOfWarrentyException.class)
	public ResponseEntity<String> productOutOfWarrentyException(ProductOutOfWarrentyException e) {
		return new ResponseEntity<String>("Product is out of warrenty",HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = EngineerNotFoundException.class)
	public ResponseEntity<String> engineerNotFoundException(EngineerNotFoundException e) {
		return new ResponseEntity<String>("No Engineer found with given Id",HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = ComplaintNotFoundException.class)
	public ResponseEntity<String> complaintNotFoundException(ComplaintNotFoundException e) {
		return new ResponseEntity<String>("No Complaint found with given Id",HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<String> productNotFoundException(ProductNotFoundException e){
		return new ResponseEntity<String>("No Product found with given Id",HttpStatus.FORBIDDEN);
	}
}
