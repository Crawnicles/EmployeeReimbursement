package com.project1.controllers;

import io.javalin.http.Handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.project1.exceptions.InvalidCredentialsException;
import com.project1.models.User;
import com.project1.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {
	
	private UserService uServ;
	
	//Objectmapper will be used to transform our java object from json and vice versa
	private ObjectMapper objectMapper;
	
	public UserController(UserService uServ) {
		this.uServ = uServ;
		objectMapper = new ObjectMapper();
	}
	
	public Handler handleRegister = (context) -> {
		
		User u = objectMapper.readValue(context.body(), User.class);
		
		System.out.println(u);
		
		uServ.registerUser(u);
		
		//Set our status code to OK
		context.status(201);
		context.result(objectMapper.writeValueAsString(u));
		
	};
	
	
	public Handler handleGetAllUsers = (context) -> {
		List<User> uList = uServ.getAllUsers();
		
		context.status(200);
		context.result(objectMapper.writeValueAsString(uList));
	};
	
	
	public Handler handleLogin = (context) -> {
		Map<String, String> body = objectMapper.readValue(context.body(), LinkedHashMap.class);
		
		//User loggedIn = uServ.loginUser(body.get("email"), body.get("password"));
		
	
		
		try { User loggedIn = uServ.loginUser(body.get("email"), body.get("password"));
			context.status(200);
			context.result(objectMapper.writeValueAsString(loggedIn));
		} catch (InvalidCredentialsException e) {
			context.status(401);
			context.result("Invalid Password");
			e.printStackTrace();
		}
		
		//Inside of the login, we probably want to store the users information inside of a cookie using 
		//the session api
		
		//Get the current users session if it exists
		//context.req().getSession();
		
		//context.req().getSession().setAttribute("user", loggedIn.getUserId());
		
		
		
	};
	
	public Handler handleUpdate = (context) -> {
		User u = objectMapper.readValue(context.body(), User.class);
	//	uServ.updateUser(u);
		
		context.status(200);
		context.result("User's information was update");
		
	};

	
	
	/*
	public Handler checkSession = (context) -> {
		
		if(context.req().getSession().getAttribute("user") == null) {
			context.status(401);
			context.result("No user is loggeed in");
			return;
		}
		
		Integer userId = (Integer) context.req().getSession().getAttribute("user");
		
		System.out.println(userId);
		
		User u = uServ.getUserByUserId(userId);
		
		context.status(200);
		context.result(objectMapper.writeValueAsString(u));
	};
	
	
	public Handler handleDelete = (context) -> {
		Map<String, String> body = objectMapper.readValue(context.body(), LinkedHashMap.class);
		
		uServ.removeUser(body.get("email"));
		
		context.status(200);
		context.result("Person was removed");
	};
	

*/
}



