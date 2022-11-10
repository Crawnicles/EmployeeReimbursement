package com.project1.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.project1.dao.UserDao;
import com.project1.exceptions.UserAlreadyExistsException;
import com.project1.exceptions.UserDoesNotExistException;
import com.project1.models.User;

//This is not production code

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
	
	UserService userService;
	@Mock
	UserDao userDao;
	
	@Before
	public void setUserService() {
		userService = new UserService(userDao);
	}
	
	@Test
	public void testGetAllRegistered() {
		List<User> testList = new ArrayList<>();
		testList.add(new User());
		testList.add(new User());
		
		when(userDao.getAllUsers()).thenReturn(testList);
		
		List<User> resultList = userService.getAllRegistered();
		
		assertEquals(testList, resultList);
		
	}
	
	@Test
	public void testLoginValid() {
		User u = new User();
		
		when(userDao.getUserByEmail(anyString())).thenReturn(u);
		
		User resultUser = userService.login("email");
		
		assertEquals(u, resultUser);
	}
	
	@Test
	public void testLoginInvalid() {
		when(userDao.getUserByEmail(anyString())).thenReturn(null);
		
		assertThrows(UserDoesNotExistException.class, ()-> userService.login("bad email"));
	}
	
	@Test
	
	public void testRegisterFails() throws Exception {
		doThrow(new SQLException()).when(userDao).addUser(any(User.class));
		
		assertThrows(UserAlreadyExistsException.class,
				()-> userService.registerUser(new User()));
		
	}
	
	@Test
	public void testRegisterAddsUser() {
		User testUser = new User();
		
		userService.registerUser(testUser);
		
		try {
		verify(userDao).addUser(testUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
