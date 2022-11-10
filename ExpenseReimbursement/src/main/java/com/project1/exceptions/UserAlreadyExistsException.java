package com.project1.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException() {
		super("The user is already registered");
	}

}
