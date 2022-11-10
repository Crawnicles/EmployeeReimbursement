package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project1.models.Ticket;
import com.project1.models.TicketStatus;
import com.project1.models.User;
import com.project1.utils.JDBCConnectionUtil;

public class TicketDaoJDBC implements TicketDao {
	
	private JDBCConnectionUtil conUtil = JDBCConnectionUtil.getInstance();
	
	
	@Override
	public void createTicket(Ticket t) {
		
		//JDBCConnectionUtil conUtil = JDBCConnectionUtil.getInstance();


		try {
			Connection connection = conUtil.getConnection();
			int status = 1;
			String sql = "INSERT INTO tickets (employeeId, description, amount, status) VALUES" + "(?,?,?,?)";
			PreparedStatement prepared = connection.prepareStatement(sql);
			
			prepared.setInt(1, t.getEmployeeId());
			prepared.setString(2, t.getDescription());
			prepared.setDouble(3, t.getAmount());
			prepared.setInt(4, status);
			prepared.execute();
			/*
			Connection connection = conUtil.getConnection();
			
			String sql = "INSERT INTO users (firstName, lastName, email, password, role) VALUES" + "(?,?,?,?,?)";
			
			PreparedStatement prepared = connection.prepareStatement(sql);
			
			prepared.setString(1, u.getFirstName());
			prepared.setString(2, u.getLastName());
			prepared.setString(3, u.getEmail());
			prepared.setString(4, u.getPassword());
			prepared.setInt(5, u.getRole().ordinal());
			
			prepared.execute();
			
		
			Connection connection = conUtil.getConnection();
			int status = 1;
			String sql = "INSERT INTO tickets(employeeId, description, amount, status) VALUES (' " 
					+ t.getEmployeeId() + "', " 
					+ t.getDescription() + ", " 
					+ t.getAmount() + ", " 
					+ status + ")";
			
			Statement statement = connection.createStatement();
			statement.execute(sql);
			
			*/	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Ticket> readAllTickets() {
		
		List<Ticket> tList = new ArrayList<>();
		
		try {
			Connection connection = conUtil.getConnection();
			String sql = "SELECT * FROM tickets";
			//PreparedStatement prepared = connection.prepareStatement(sql);
			//ResultSet result = prepared.executeQuery();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				Ticket t = new Ticket();
				t.setTicketId(result.getInt(1));
				t.setEmployeeId(result.getInt(2));
				t.setDescription(result.getString(3));
				t.setAmount(result.getDouble(4));
				if(result.getInt(5)  == 1) {
					t.setStatus(TicketStatus.PENDING);
				} else if(result.getInt(5) == 2) {
					t.setStatus(TicketStatus.APPROVED);
				} else {
					t.setStatus(TicketStatus.DENIED);
				}
				
				//TicketStatus.valueOf(result.getString("status"));
				// this setStatus refers to the string implementation, not the enum options
				
				/*
				if(result.getInt(4) == null) {
					t.setManager(null);
				} else {
					
					User u = new User();
					u.setUserId(result.getInt(4));
					t.setManager(u);
				}
				*/
				tList.add(t);
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tList;
	}

	@Override
	public List<Ticket> getTicketByStatus(TicketStatus status) {
		List<Ticket> tList = new ArrayList<>();
		
		try {
			
			Connection connection = conUtil.getConnection();
			
			String sql = "SELECT * FROM tickets WHERE status = " + status;
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				Ticket t = new Ticket();
				
				t.setTicketId(result.getInt(1));
				t.setEmployeeId(result.getInt(2));
				t.setDescription(result.getString(3));
				t.setAmount(result.getDouble(4));
				if(result.getInt(5)  == 1) {
					t.setStatus(TicketStatus.PENDING);
				} else if(result.getInt(5) == 2) {
					t.setStatus(TicketStatus.APPROVED);
				} else {
					t.setStatus(TicketStatus.DENIED);
				}
				tList.add(t);
				
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tList;
	}

	/*
	@Override
	public void deleteTicket(int ticketId) {
		// TODO Auto-generated method stub
		try {
			
			Connection connection = conUtil.getConnection();
			
			String sql = "DELETE FROM tickets WHERE ticketid=?";
			
			PreparedStatement prepared = connection.prepareStatement(sql);
			
			prepared.setInt(1, ticketId);
			
			prepared.execute();
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateTicket(int ticketId, TicketStatus updatedStatus) {
		
		
		try {
			
			Connection connection = conUtil.getConnection();
			String sql = "UPDATE tickets SET status =? WHERE ticketId = ?";
			
			PreparedStatement prepared = connection.prepareStatement(sql);
			
		
			
			
			if(updatedStatus.equals(TicketStatus.APPROVED)) {
				prepared.setInt(1, 2);
			} else {
				prepared.setInt(1, 3);
			}
			prepared.setInt(2, ticketId);
		
			prepared.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
*/	
	

	@Override
	public void updateTicket(Ticket t) {
		try {
			
			Connection connection = conUtil.getConnection();
			
			String sql = "UPDATE tickets SET status = ? WHERE ticketid = ?";
			
			PreparedStatement prepared = connection.prepareStatement(sql);
			
			prepared.setInt(1, t.getStatus().ordinal());
			prepared.setInt(2, t.getTicketId());

			prepared.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

