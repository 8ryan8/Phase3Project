package com.simplilearn.sportshoeswebservice.sportshoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value=HttpStatus.NOT_FOUND)
public class CategoryNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CategoryNotFound(String message) {
		super(message);
	}
}
