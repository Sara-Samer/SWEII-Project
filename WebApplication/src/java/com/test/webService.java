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
    //@GET
    //@Path("/getlist")
    //@Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserModel> getList() throws SQLException, ClassNotFoundException{
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
    public String register(String userName, String email, String password, String type) throws ClassNotFoundException, SQLException{        

        Connection con = null;
        
        String query = "INSERT INTO userTable(name,email,password,type) values ('" + userName+ "','" +email +"','"+password +"','" +type+"')";
        Class.forName("org.apache.derby.jdbc.ClientDriver");              
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/DB");
        con.prepareStatement(query).execute();
        return "User Added Successfully.";
    }
    
    
}
