package com.SWE.OnlineStorePlatform.services;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

	@RequestMapping("/get-user-list")
	public String getRegisteredUsers() {
		return null;
	}
	@RequestMapping("/add-user")
	public Boolean registerUser() {
		return null;
	}
}
