package com.project1.dao;

import java.util.List;
import com.project1.models.User;


public interface UserDao {
	public void registerUser(User u) throws Exception;
	//public void reigsterUser(User u) throws Exception;
	public List<User> getAllUsers();
	//public User loginUser(String email, String password);
	public User getUserByEmail(String email);
	public User getUserByUserId(int userId);
	public void deleteUser(String email);
	public void updateUser(User u);
	//User getUserByID(int userId);
	//public User getUserByID(int userId);
	

}
