package com.SWE.OnlineStorePlatform.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.minidev.json.JSONObject;

import com.SWE.OnlineStorePlatform.models.*;
import com.SWE.OnlineStorePlatform.services.TokenService;
import com.SWE.OnlineStorePlatform.services.UserService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.minidev.json.JSONObject;

@RestController
public class UsersController {

	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;

	@RequestMapping("/get-user-list")
	public List<User> getRegisteredUsers() {
		List<User> userList = userService.listAll();
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

		switch (type) {
			case 0: {
				user = new Buyer(email, username, pass, Type.BUYER);
				break;
			}
			case 1: {
				user = new Owner(email, username, pass, Type.OWNER);
				break;
			}
			default:
				user = null;
				break;
		}

		if (!isValid || user == null) {
			return false;
		} else {
			userService.save(user);
			{
				return true;
			}
		}

	}

	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(HttpServletRequest request) throws ParseException {
		String email_username = request.getParameter("email_username");
		String pass = request.getParameter("password");

		User user = userService.checkUserLogin(email_username);
		JSONObject json = new JSONObject();
		Token loginToken = new Token(email_username, pass, 512);
		if (user == null) {
			json.put("response:", "User Not Found.");
			return json;
		}
		if (user.getPassword().equals(pass)) {
			json.put("response:", "User Logged In Successfully.");
			return json;
		}
		json.put("response:", "Wrong Password.");
		return json;

	}
}