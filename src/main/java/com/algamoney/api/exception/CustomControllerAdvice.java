package com.algamoney.api.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CategoriaNotFoundException.class)
	public ResponseEntity<Object> handleCategoriaNotFound(CategoriaNotFoundException ex, WebRequest request){
		MessagesExceptionHandler errorMessages = new MessagesExceptionHandler(new Date(),HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<Object>(errorMessages,new HttpHeaders(),errorMessages.getStatus());
	}
	
	@ExceptionHandler(PessoaNotFoundException.class)
	public ResponseEntity<Object> handlePessoaNotFound(PessoaNotFoundException ex, WebRequest request){
		MessagesExceptionHandler errorMessages = new MessagesExceptionHandler(new Date(),HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<Object>(errorMessages,new HttpHeaders(),errorMessages.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(message -> message.getDefaultMessage())
				.collect(Collectors.toList())
				;
		
		MessagesExceptionHandler errorMessages = new MessagesExceptionHandler(new Date(),status.value(), errors);
		
		return new ResponseEntity<Object>(errorMessages,HttpStatusCode.valueOf(errorMessages.getStatus()));
	}
}
