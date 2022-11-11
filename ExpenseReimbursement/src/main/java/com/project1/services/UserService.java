package com.project1.services;

import java.util.ArrayList;
import java.util.List;

import com.project1.dao.UserDao;
import com.project1.exceptions.InvalidCredentialsException;
import com.project1.exceptions.UserAlreadyExistsException;
import com.project1.exceptions.UserDoesNotExistException;
import com.project1.models.User;
import com.project1.utils.LoggingUtil;

public class UserService {
	
	private UserDao userDao;
	
	//Dependency injection, it allows us to change components of the same 'type' easily
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	
	/*
	public void registerUser(User u) {
		List<User> uList = new ArrayList<>();
		
		uList = userDao.getAllUsers();
		
		
		for (User listUser : uList) {
			if (listUser.getEmail()==u.getEmail()) {
				throw new UserAlreadyExistsException();
			}
		}
		
		userDao.registerUser(u);
	}
		
		*/
	public void registerUser(User u) throws Exception {
		
		 List<User> uList = new ArrayList<>();
		 
		 uList = userDao.getAllUsers();
		 
		 for (User listUser : uList) {
		 			if (listUser.getEmail().equals(u.getEmail())) {
		 					throw new UserAlreadyExistsException();
		 			}
		 }
		 
		 userDao.registerUser(u);
		/*
		try{
			userDao.registerUser(u);
			LoggingUtil.getLogger().warn("User: " + u + "was registered");
		} catch(Exception e) {
			LoggingUtil.getLogger().warn("User with email" + u.getEmail() + "tried to register a second time");
			throw new UserAlreadyExistsException();
		} */
	}
	
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	

	public User loginUser(String email, String password) { 
		/*
		User u = null;
		
		try {
			u =userDao.getUserByEmail(email);
			u.getPassword().equals(password);
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
		}
		return u;
		*/
		
		User u = userDao.getUserByEmail(email);
		
		if(u == null) {
			LoggingUtil.getLogger().warn("Invalid email" + email);
			throw new UserDoesNotExistException();
		} else if(u.getPassword().equals(password)) {
			return u;
		} else  {
			throw new InvalidCredentialsException();
		}
		
		//LoggingUtil.getLogger().info("User " + email + " logged in");
		//return u;
		
		
	}
	
	//public User getUserByUserId(int userId);	
	
	
	/*
	public List<User> getAllRegistered(){
		return userDao.getAllUsers();
	}
	
	public void removeUser(String email) {
		userDao.deleteUser(email);
		LoggingUtil.getLogger().info("User " + email + " was removed from the system");
	}
	
	
	public void updateUser(User u) {
		userDao.updateUser(u);
		LoggingUtil.getLogger().info("User " + u.getUserId() + " was updated");
	}
	
	*/

}
