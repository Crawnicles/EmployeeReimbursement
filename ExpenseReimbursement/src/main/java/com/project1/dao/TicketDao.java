package com.project1.dao;

import java.util.List;
import com.project1.models.Ticket;
import com.project1.models.TicketStatus;

public interface TicketDao {
	
	public List<Ticket> readAllTickets();
	public void createTicket(Ticket t);
	//public void deleteTicket(int id);
	public List<Ticket> getTicketByStatus(TicketStatus status);
	public void updateTicket(Ticket t);
}

