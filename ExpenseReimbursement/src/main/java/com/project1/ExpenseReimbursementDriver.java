package com.project1;


import com.project1.controllers.UserController;
import com.project1.controllers.TicketController;
import com.project1.dao.TicketDao;
import com.project1.dao.TicketDaoJDBC;
import com.project1.dao.UserDao;
import com.project1.dao.UserDaoJDBC;
import com.project1.exceptions.TicketAlreadyProcessedException;
import com.project1.exceptions.UserAlreadyExistsException;
import com.project1.exceptions.UserDoesNotExistException;
import com.project1.services.TicketService;
import com.project1.services.UserService;

import io.javalin.Javalin;

public class ExpenseReimbursementDriver {


	public static void main(String args[]) {
		
		UserDao uDao = new UserDaoJDBC();
		UserService uServ = new UserService(uDao);
		UserController uController = new UserController(uServ);
		TicketDao tDao = new TicketDaoJDBC();
		TicketService tServ = new TicketService(tDao);
		TicketController tController = new TicketController(tServ);
	
		Javalin app = Javalin.create(config -> {
			config.plugins.enableCors(cors -> {
				cors.add(it -> {
					it.anyHost();
				});
			});
		});
		
		//uDao.addUser(new User(UserRole.EMPLOYEE, "Andrew", "Crawford", "andrew@mail.com", "password"));
		
		app.get("/hello", (ctx) -> ctx.result("Hello, we are making our first get request"));
		app.post("/user/register", uController.handleRegister);
		app.get("user/", uController.handleGetAllUsers);
		app.post("/user/login", uController.handleLogin);
		//app.delete("/user/", uController.handleDelete);
		//app.put("/user/", uController.handleDelete);
		//app.put("/user/", uController.handleUpdate);
		//app.get("/user/session", uController.han)
		
		
		app.post("/ticket/create", tController.handleCreate);

		//app.post("/ticket/status", tController.getTicketByStatus);
		//app.get("/ticket", tController.handleRead);
		app.put("/ticket/update", tController.handleUpdate);
		
		
		//We can also register handlers to deal with exceptions
		app.exception(UserDoesNotExistException.class, (e, context) -> {
			context.status(401);
			context.result("You were unable to login, User does not exist");
		});
		
		app.exception(UserAlreadyExistsException.class, (e, context) -> {
			context.status(409);
			context.result("You are not able to register an account with an email which already exists");
		});
		
		app.exception(TicketAlreadyProcessedException.class, (e, context) -> {
			context.status(409);
			context.result("Ticket already processed");
		});
		
		
		app.start(8000);
		
	}

}