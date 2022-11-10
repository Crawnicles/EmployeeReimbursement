package com.project1.exceptions;

public class TicketAlreadyProcessedException extends RuntimeException {
	
	public TicketAlreadyProcessedException() {
		super("The ticket was already processed");
	}
}
