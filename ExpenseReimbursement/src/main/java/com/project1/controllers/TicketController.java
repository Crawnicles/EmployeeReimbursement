package com.project1.controllers;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.project1.exceptions.InvalidCredentialsException;
import com.project1.exceptions.TicketAlreadyProcessedException;
import com.project1.models.Ticket;
import com.project1.models.TicketStatus;
import com.project1.models.User;
import com.project1.services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Handler;

public class TicketController {
	
	private TicketService tServ;
	private ObjectMapper om;
	
	
	public TicketController(TicketService tServ) {
		this.tServ = tServ;
		om = new ObjectMapper();
	}
	
	public Handler handleCreate = (context) -> {
		
		Ticket t = om.readValue(context.body(), Ticket.class);
		tServ.createTicket(t);
		
		context.status(201);
		context.result("New ticket created");
		
		
	};
	
	public Handler handleRead = (context) -> {
		List<Ticket> tList = tServ.getAllTickets();
		context.status(200);
		context.result(om.writeValueAsString(tList));
	};
	

	public Handler handleUpdate = (context) -> {
		Ticket t = om.readValue(context.body(), Ticket.class);
		
		System.out.println(t);
		tServ.updateTicket(t);
		
		context.status(200);
		context.result("Ticket was updated ");
		//check the ticket before passing it in
		
		/*
		//Use a getTicketById method to get the ticket you want, then pass it to the try catch block
		System.out.println(t);
		try {
			if(t.getStatus().equals(TicketStatus.APPROVED) || t.getStatus().equals(TicketStatus.DENIED)) {
			throw new TicketAlreadyProcessedException();
			}
		} catch (TicketAlreadyProcessedException e) {
		context.status(401);
		context.result("Ticket was already processed");
		e.printStackTrace();
		return;
		}
		//not returning out of catch block
		*/

	
	};
	
	
	/*
	public Handler getTicketByStatus = (context) -> {
		HashMap<String, String> body = om.readValue(context.body(), LinkedHashMap.class);

		
		// if case matches s
		//Your ticket service method was looking for a TicketStatus enum,
		//so there is no need to create an entirely new ticket in the controller
		
		
		TicketStatus status;
		switch (body.get("status")) {
		case "PENDING":
			status = TicketStatus.PENDING;
			break;
		case "APPROVED":
			status = TicketStatus.APPROVED;
			break;
		case "DENIED":
			status = TicketStatus.DENIED;
			break;
		default:
			System.out.println("Invalid status");
		}
	
		
		
		//List<Ticket> tList = tServ.getTicketByStatus(body.get("status"));
		
		context.status(200);
		//context.result(om.writeValueAsString(tList));
	};
	
	*/
	
	/*	
	public Handler handleUdpate1 = (context) -> {
		HashMap<String, Integer> body = om.readValue(context.body(), LinkedHashMap.class);
		
		Ticket t = tServ.getTicketByID(body.get("id"));
		if(t.getStatus().equals(TicketStatus.PENDING)) {
			tServ.approveTicket(body.get("id"));
			context.status(200);
			context.result("Ticket " + body.get("id") +  " has been approved");
			return;
		}
		
		context.status(400);
		context.result("Sorry, that ticket is no longer pending.");
		
	};
	

	public Handler handleUpdate = (context) -> {
		
		Map<String, T> body = om.readValue(context.body(), LinkedHashMap.class);
		
		int ticketId = (int) body.get("ticketId");
		TicketStatus updatedStatus = TicketStatus.valueOf(body.get("updatedStatus").toString());
		tServ.updateTicket(ticketId, updatedStatus);
		context.status(200);
		context.result("Ticket was updated");
	};
	
	
	
	public Handler handleDelete = (context) -> {
		LinkedHashMap<String, Integer> body = om.readValue(context.body(), LinkedHashMap.class);
		
		tServ.deleteTicket(body.get("ticketId"));
		
		context.status(200);
		context.result("Ticket was removed");
		
		
		
		
	};
	
	public Handler handleUpdateTicketStatus = (context) -> {
		Ticket t = om.readValue(context.body(), Ticket.class);
		System.out.println(t);
		//tServ.updateTicket(1, TicketStatus.PENDING);;
		tServ.updateTicket2(t);
		context.status(200);
		context.result("Ticket status was updated");
	};
	
	*/

}

