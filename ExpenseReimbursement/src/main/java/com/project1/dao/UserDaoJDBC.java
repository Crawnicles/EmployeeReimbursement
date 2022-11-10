package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project1.models.User;
import com.project1.models.UserRole;
import com.project1.utils.JDBCConnectionUtil;

public class UserDaoJDBC  implements UserDao {
	
	private JDBCConnectionUtil conUtil = JDBCConnectionUtil.getInstance();
	/*

	@Override
	public void registerUser(User u) throws SQLException{
		
		try {
		
		Connection connection = conUtil.getConnection();
		
		//int role = u.getRole().ordinal() + 1;
		
		String sql = "INSERT INTO users (firstName, lastName, email, password, role) VALUES"
				+ "('" + u.getFirstName() +
				"','" + u.getLastName() +
				"','" + u.getEmail() +
				"','" + u.getPassword() +
				"','" + u.getRole().ordinal() + "')";
		
		// Get the statement to actually make our query
		Statement statement = connection.createStatement();
		
		statement.execute(sql);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	*/
	@Override
	public void registerUser(User u) {
		try {
			Connection connection = conUtil.getConnection();
			
			String sql = "INSERT INTO users (firstName, lastName, email, password, role) VALUES" + "(?,?,?,?,?)";
			
			PreparedStatement prepared = connection.prepareStatement(sql);
			
			prepared.setString(1, u.getFirstName());
			prepared.setString(2, u.getLastName());
			prepared.setString(3, u.getEmail());
			prepared.setString(4, u.getPassword());
			prepared.setInt(5, u.getRole().ordinal());
			
			prepared.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public List<User> getAllUsers() {
		
		List<User> uList = new ArrayList<>();
		
		try {
			
			// We need a connection to create a statement
			Connection connection = conUtil.getConnection();
			
			String sql = "SELECT * FROM users";
			
			Statement statement = connection.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				User u = new User();
				u.setUserId(result.getInt(1));
				u.setFirstName(result.getString(2));
				u.setLastName(result.getString(3));
				u.setEmail(result.getString(4));
				u.setPassword(result.getString(5));
				
				if(result.getInt(6) == 1) {
					u.setRole(UserRole.MANAGER);
				} else {
					u.setRole(UserRole.EMPLOYEE);
				}
				
				uList.add(u);
				
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}

	@Override
	public User getUserByEmail(String email) {
		User u = null;
		
		try {
			
			Connection connection = conUtil.getConnection();
			
			String sql = "SELECT * FROM users WHERE email ='" + email + "'";
			
			//PreparedStatement prepared = connection.prepareStatement(sql);
			//prepared.setString(1, email);
			
			Statement statement = connection.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				u = new User();
				u.setUserId(result.getInt(1));
				u.setFirstName(result.getString(2));
				u.setLastName(result.getString(3));
				u.setEmail(result.getString(4));
				u.setPassword(result.getString(5));
				if(result.getInt(6) == 1) {
					u.setRole(UserRole.MANAGER);
				}else {
						u.setRole(UserRole.EMPLOYEE);
				}
		
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void deleteUser(String email) {
		// TODO Auto-generated method stub'
		
		try {
			
			Connection connection = conUtil.getConnection();
			
			String sql = "DELETE FROM users WHERE email ='" + email + "'";
			
			Statement statement = connection.createStatement();
			
			statement.execute(sql);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateUser(User u) {
		

		try {
			
			Connection connection = conUtil.getConnection();
			
			int role = u.getRole().ordinal() +1;
			
			String sql = "UPDATE users SET firstName = '" + u.getFirstName() +
					"', lastName'" + u.getLastName() +
					"', email='" + u.getEmail() +
					"', password='" + u.getPassword() +
					"', role =" + role +
					" WHERE userId=" + u.getUserId();
			
			Statement statement = connection.createStatement();
			
			statement.execute(sql);
			
		} catch(SQLException e) {
			e.printStackTrace();
	
		}
		
	}
	
	@Override
	public User getUserByUserId(int userId) {
		
			User u = null;
			
			try {
				
				Connection connection = conUtil.getConnection();
				
				String sql = "SLECT * FROM users WHERE userId=?";
				
				PreparedStatement prepared = connection.prepareStatement(sql);
				
				prepared.setInt(1, userId);
				
				ResultSet result = prepared.executeQuery();
				
				while(result.next()) {
					u = new User();
					u.setUserId(result.getInt(1));
					u.setFirstName(result.getString(2));
					u.setLastName(result.getString(3));
					u.setEmail(result.getString(4));
					u.setPassword(result.getString(5));
					if(result.getInt(6) == 1) {
						u.setRole(UserRole.MANAGER);
						
					} else {
						u.setRole(UserRole.EMPLOYEE);
					}
					
					
				}
					
			} catch(SQLException e) {
					e.printStackTrace();
			}
				
				return u;
	}

	
}


	
/*
	@Override
	public User getUserById(int userId) {
		User u = null;
		
		try {
			
			Connection connection = conUtil.getConnection();
			
			String sql = "SLECT * FROM users WHERE userId=?";
			
			PreparedStatement prepared = connection.prepareStatement(sql);
			
			prepared.setInt(1, userId);
			
			ResultSet result = prepared.executeQuery();
			
			while(result.next()) {
				u = new User();
				u.setUserId(result.getInt(1));
				u.setFirstName(result.getString(2));
				u.setLastName(result.getString(3));
				u.setEmail(result.getString(4));
				u.setPassword(result.getString(5));
				if(result.getInt(6) == 1) {
					u.setRole(UserRole.MANAGER);
					
				} else {
					u.setRole(UserRole.EMPLOYEE);
				}
				

			}
				
		} catch(SQLException e) {
				e.printStackTrace();
		}
			
			return u;
	}

}
*/