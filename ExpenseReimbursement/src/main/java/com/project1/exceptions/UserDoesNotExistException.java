package com.project1.exceptions;

public class UserDoesNotExistException extends RuntimeException {
	
	public UserDoesNotExistException() {
		super("The user you are searching for does not exist");
	}

}
