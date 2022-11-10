package com.project1.models;

public class Ticket /*implements Serializable*/ 
{
	
	//private static final long serialVersionUID = 1L;
	
	private int ticketId;
	private int employeeId;
	private String description;
	private double amount;
	private TicketStatus status;
	
	
	public Ticket() {
		super();
	}


	public Ticket(int ticketId, int employeeId, String description, double amount, TicketStatus status) {
		super();
		this.ticketId = ticketId;
		this.employeeId = employeeId;
		this.description = description;
		this.amount = amount;
		this.status = status;
	}


	public Ticket(int employeeId, String description, double amount, TicketStatus status) {
		super();
		this.employeeId = employeeId;
		this.description = description;
		this.amount = amount;
		this.status = status;
	}


	public Ticket(int employeeId, String description, double amount) {
		super();
		this.employeeId = employeeId;
		this.description = description;
		this.amount = amount;
	}


	public int getTicketId() {
		return ticketId;
	}


	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public TicketStatus getStatus() {
		return status;
	}


	public void setStatus(TicketStatus status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", employeeId=" + employeeId + ", description=" + description
				+ ", amount=" + amount + ", status=" + status + "]";
	}
	
	
}



	
	