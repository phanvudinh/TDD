package com.axonactive.kiss.TDD.exception;

import com.axonactive.kiss.TDD.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Response> NotFoundExceptionHandler(Exception ex) {
		Response error = new Response();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<Response>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Response> NotValidExceptionHandler(Exception ex) {
		Response error = new Response();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("The request could not be understood by the server due to malformed syntax.");
		return new ResponseEntity<Response>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> exceptionHandler(Exception ex) {
		Response error = new Response();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("internal server error");
		return new ResponseEntity<Response>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//Do something like this for another exception
}
