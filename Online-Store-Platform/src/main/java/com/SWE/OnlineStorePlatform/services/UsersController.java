package com.SWE.OnlineStorePlatform.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SWE.OnlineStorePlatform.models.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@RestController
public class UsersController {

	@Autowired
	private UserService service;
	@RequestMapping("/get-user-list")
	public List<User> getRegisteredUsers() {
		List<User> userList = service.listAll();
		return userList;
	}
	
	
	@RequestMapping("/register")
	public Boolean registerUser(HttpServletRequest request) {
		String emailPattern = "^([a-zA-Z0-9_\\.]+)@[a-zA-Z_]+?\\.[a-zA-Z]{2,4}$";
		String usernamePattern = "[a-zA-Z0-9_\\-\\.]";
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String typeString = request.getParameter("type");
		int type = Integer.parseInt(typeString);
		User user;
		boolean isValid = username.matches(usernamePattern) || email.matches(emailPattern);
		
		switch(type) {
			case 0:{
				user = new Buyer(email, username, pass, Type.BUYER);
				break;
			}
			case 1:{
				user = new Owner(email, username, pass, Type.OWNER);
				break;
			}
			default:
				user = null;
				break;
		}
		
		if(!isValid || user == null)
			{return false;}
		else {
			service.save(user);
			{return true;}
		}
			
	}

	@RequestMapping("/login")
	@ResponseBody
	public ResponseEntity<User> login(HttpServletRequest request) {
		String email_username = request.getParameter("email_username");
		String pass = request.getParameter("password");
		
		User user = service.checkUserLogin(email_username);
		
		HttpHeaders headers = new HttpHeaders();
		//List<String> str = new ArrayList<String>();
		//str.add("User Not Found.\n");
	    //headers.put("error", str);
	    headers.add("Content-Type", "application/json");
	    ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok().headers(headers);
	    if(user == null)
			return responseBuilder.build();//{return new ResponseEntity<>(headers, HttpStatus.OK);}//"User Doen't exist.\n";
		if(user.getPassword().equals(pass))
			return new ResponseEntity<User>(user, HttpStatus.OK);//"User loged in successfully.\n";
		return responseBuilder.build();//body(new EmptyJsonBody());//{return new ResponseEntity<>(headers, HttpStatus.OK);}//"Wrong email or password, Please try again.\n";
		
	}
	
//	return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);

//return new ResponseEntity(HttpStatus.NOT_FOUND);
	
	/*@GetMapping("/login/{email_username},{password}")
	public ResponseEntity<User> read(@PathVariable("email_username") String email_username, @PathVariable("password") String password) {
	    User user = service.checkUserLogin(email_username);
	    if (user == null) {
	        return ResponseEntity.notFound().build();
	    } else if(user.getPassword().equals(password)){
	        return ResponseEntity.ok(user);
	    }
	    return ResponseEntity.badRequest().build();
	}*/

}
