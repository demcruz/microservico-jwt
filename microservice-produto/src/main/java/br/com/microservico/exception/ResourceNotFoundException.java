package br.com.microservico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4398092497515090464L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}
}
