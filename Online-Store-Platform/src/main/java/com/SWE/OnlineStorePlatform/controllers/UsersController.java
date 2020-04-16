package com.SWE.OnlineStorePlatform.controllers;

import javax.servlet.http.HttpServletRequest;

import com.SWE.OnlineStorePlatform.models.Admin;
import com.SWE.OnlineStorePlatform.models.Buyer;
import com.SWE.OnlineStorePlatform.models.Owner;
import com.SWE.OnlineStorePlatform.models.Token;
import com.SWE.OnlineStorePlatform.models.Type;
import com.SWE.OnlineStorePlatform.models.User;
import com.SWE.OnlineStorePlatform.services.TokenService;
import com.SWE.OnlineStorePlatform.services.UserService;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
public class UsersController {

	@Autowired
	protected UserService userService;
	@Autowired
	private TokenService tokenService;

	@RequestMapping("/register")
	public JSONObject registerUser(HttpServletRequest request) {
		String emailPattern = "^([a-zA-Z0-9_\\.]+)@[a-zA-Z_]+?\\.[a-zA-Z]{2,4}$";
		String usernamePattern = "[a-zA-Z0-9_\\-\\.]";
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String typeString = request.getParameter("type");
		int type = Integer.parseInt(typeString);
		User user;
		boolean isValid = username.matches(usernamePattern) || email.matches(emailPattern);
		JSONObject json = new JSONObject();

		switch (type) {
			case 0: {
				user = new Buyer(email, username, pass, Type.BUYER);
				break;
			}
			case 1: {
				user = new Owner(email, username, pass, Type.OWNER);
				break;
			}
			case 2: {
				// For testing purposes
				user = new Admin(email, username, pass, Type.ADMIN);
				break;
			}
			default:
				user = null;
				break;
		}

		if (!isValid || user == null) {
			json.put("message", "please check to enter a correct type and valid email/username");
			json.put("data", false);
		} else {
			userService.save(user);
			json.put("message", "User Created successfully");
			json.put("data", true);
		}
		return json;
	}

	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(HttpServletRequest request) throws ParseException {
		String email_username = request.getParameter("email_username");
		String pass = request.getParameter("password");

		User user = userService.checkUserLogin(email_username);
		JSONObject json = new JSONObject();
		if (user == null) {
			json.put("message:", "User Not Found.");
			return json;
		}
		Token newLoginToken = new Token(email_username, pass, 'S', user.getEnumType());
		Token oldToken = tokenService.get(newLoginToken.getToken());
		if (oldToken == null) {
			tokenService.addToken(newLoginToken);
		} else {
			if (oldToken.isValid()) {
				newLoginToken = oldToken;
			} else {
				tokenService.delete(oldToken.getToken());
				tokenService.addToken(newLoginToken);
			}
		}
		if (user.getPassword().equals(pass)) {
			json.put("message:", "User Logged In Successfully.");
			json.put("JWT:", newLoginToken);
			return json;
		}
		json.put("message:", "Wrong Password.");
		return json;

	}
}
