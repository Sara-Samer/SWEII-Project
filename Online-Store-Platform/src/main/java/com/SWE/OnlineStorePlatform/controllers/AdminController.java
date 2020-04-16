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
public class AdminController extends UsersController {


	@RequestMapping("/get-user-list")
	public List<User> getRegisteredUsers() {
		List<User> userList = userService.listAll();
		return userList;
	}


}
