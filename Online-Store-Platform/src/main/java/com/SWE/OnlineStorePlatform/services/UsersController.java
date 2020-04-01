package com.SWE.OnlineStorePlatform.services;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.SWE.OnlineStorePlatform.models.User;


@RestController
public class UsersController {

	@Autowired
	private UserService service;
	@RequestMapping("/get-user-list")
	public String getRegisteredUsers() {
		List<User> userList = service.listAll();
		return null;
	}
	@RequestMapping("/register")
	public Boolean registerUser(HttpServletRequest request) {
		return null;
	}
}
