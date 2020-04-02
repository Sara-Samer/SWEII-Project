package com.SWE.OnlineStorePlatform.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "user")
abstract public class User {
	@Id
	//@GeneratedValue(generator="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long ID;
    private String email;
    private String username;
    private String password;
    private Type type;
 
    public User() {
    }
    public User(String email, String username, String password, Type type) {
    	this.email = email;
    	this.username = username;
    	this.password = password;
    	this.type = type;
    }
}

