package com.project1.exceptions;

public class InvalidCredentialsException extends RuntimeException {
	
	public InvalidCredentialsException() {
		super("Incorrect password");
	}

}
