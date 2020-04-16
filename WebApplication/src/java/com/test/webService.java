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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lenovo
 */
public class webService {
    
    public ArrayList<UserModel> getRegisteredUsers() throws SQLException, ClassNotFoundException{
        ArrayList<UserModel> allRecords = new ArrayList<>();
        Connection con = null;
        String query = "select * from userTable";
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB");
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        
        while(result.next()){
            UserModel user = new UserModel();
            user.setName(result.getString("name"));
            user.setEmail(result.getString("email"));
            user.setPassword(result.getString("password"));
            user.setType(result.getString("type"));
            allRecords.add(user);
        }
        
        
        return allRecords;
    }
    public String registerUser(String userName, String email, String password, String type) throws ClassNotFoundException, SQLException{        
        if(!email.contains("@") || !email.endsWith(".com") || email.endsWith("@.com"))
            return "Invalid Email.";
        
        Connection con = null;
        String query = "select * from userTable where email = '" + email + "'";
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB");
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        
        if(result.next()){
            return "Email already Exists.";
        }
        
        query = "INSERT INTO userTable(name,email,password,type) values ('" + userName+ "','" +email +"','"+password +"','" +type+"')";
        Class.forName("org.apache.derby.jdbc.ClientDriver");              
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB");
        con.prepareStatement(query).execute();
        return "User Added Successfully.";
    }
    
    
}
