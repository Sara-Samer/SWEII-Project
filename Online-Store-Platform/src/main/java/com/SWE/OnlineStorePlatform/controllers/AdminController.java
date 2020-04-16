package com.SWE.OnlineStorePlatform.controllers;

import com.SWE.OnlineStorePlatform.models.*;
import com.SWE.OnlineStorePlatform.services.TokenService;
import com.SWE.OnlineStorePlatform.services.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/get-user-list")
    public JSONObject getRegisteredUsers(HttpServletRequest request) {
        String token = request.getParameter("token");
        Token tokenObj = tokenService.get(token);
        JSONObject json = new JSONObject();
        if (tokenObj.isValid() && tokenObj.getType() == Type.ADMIN) {
            List<User> userList = userService.listAll();
            json.put("message:", "User list retrieved successfully.");
            json.put("data:", userList);
            return json;
        }
        json.put("message:", "Can't process Request.");
        json.put("data:", null);
        return json;
    }

}
