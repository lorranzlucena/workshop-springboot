package com.projeto.course.services.exceptions;

/**
 * classe para tratar as excessões do pocote service.
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource not Found id : "+ id);
	}
	
}
