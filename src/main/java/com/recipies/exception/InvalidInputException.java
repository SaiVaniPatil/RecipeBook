package com.recipies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {
	private static final long serialVersionUID = 7246983447306271526L;

	public InvalidInputException(String message) {
		super(message);
	}

}
