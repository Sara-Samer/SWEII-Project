/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lenovo
 */

@Path("userController")
public class UserController {
    @GET
    @Path("/getlist")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserModel> getList() throws SQLException, ClassNotFoundException{
        webService web = new webService();
        return web.getList();
        
    }
    
    
    private enum userType{
        ADMIN,SHOPOWNER, BUYER;
}
    @GET
    @Path("/register/{userName}/{email}/{password}/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String registerUser(@PathParam("userName") String userName, @PathParam("email")String email, @PathParam("password")String password, @PathParam("type")int type) throws ClassNotFoundException, SQLException{
        webService web = new webService();
        if(type == 1 || type == 2)
            return web.register(userName, email, password, userType.values()[type].toString());
        else
            return "User type is Invalid";
    }
}
