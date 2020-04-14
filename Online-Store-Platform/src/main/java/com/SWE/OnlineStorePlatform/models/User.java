package com.SWE.OnlineStorePlatform.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long ID;
	@Column(name = "email", unique = true)
    public String email;
    @Column(name = "username", unique = true)
    public String username;
    @Column(name = "password")
    public String password;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public Type type;
 
    public User() {
    }
    public User(String email, String username, String password, Type type) {
    	this.email = email;
    	this.username = username;
    	this.password = password;
    	this.type = type;
    }
}

