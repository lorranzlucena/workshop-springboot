package com.projeto.course.resource.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projeto.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice// responsavel por interceptar as exceções 
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)//irá interceptar qualquer exceção desse tipo e ira fazer o tratamento
	public ResponseEntity<StandardError> resouceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		
		String error = "Resource Not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)//irá interceptar qualquer exceção desse tipo e ira fazer o tratamento
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		
		String error = "Data base ERROR";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(
				Instant.now(), // Data/hora do erro
				status.value(), // Código HTTP (ex: 400)
				error, // Mensagem de erro genérica
				e.getMessage(),// Mensagem específica da exceção
				request.getRequestURI()); // Qual endpoint gerou o erro
		return ResponseEntity.status(status).body(err);
	}
}
