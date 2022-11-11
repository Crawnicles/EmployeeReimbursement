package com.project1.services;

import java.util.List;


import com.project1.dao.TicketDao;
import com.project1.dao.UserDao;
import com.project1.exceptions.TicketAlreadyProcessedException;
import com.project1.models.Ticket;
import com.project1.models.TicketStatus;
import com.project1.utils.LoggingUtil;

public class TicketService {
	
	private TicketDao tDao;
	
	public TicketService(TicketDao tDao ) {
		this.tDao = tDao;
	}
	
	public void createTicket(Ticket t) {
		tDao.createTicket(t);
		LoggingUtil.getLogger().info("New ticket was created: " + t);
	}
	
	
	public void updateTicket(Ticket t) {
		
		/*
		if(t.getStatus().equals(TicketStatus.APPROVED) || t.getStatus().equals(TicketStatus.DENIED)) {
			throw new TicketAlreadyProcessedException();
		}
		*/
	
		//add a current status and compare to a new status
		tDao.updateTicket(t);
		LoggingUtil.getLogger().info("Ticket was updated!" + t);
	}
	
	public List<Ticket> getAllTickets() {
		return tDao.readAllTickets();
		
		/*
		for(int i = 0; i<tList.size(); i++) {
			Ticket t = tList.get(i);
			if(t.getManager() != null) {
				t.setManager(uDao.getUserById(t.getManager().getUserId()));
				tList.set(i,t);
			}
		}
		return tList;
		*/
	}

	
	public void deleteTicket(int ticketId) {
		//tDao.deleteTicket(ticketId);
		LoggingUtil.getLogger().info("Course" + ticketId );
	}
	
	
	public List<Ticket> getTicketByStatus(TicketStatus status) {
		return tDao.getTicketByStatus(status);
	}
	
	public Ticket getTicketByID(int ticketId) {
		return tDao.getTicketById(ticketId);
	}

}
