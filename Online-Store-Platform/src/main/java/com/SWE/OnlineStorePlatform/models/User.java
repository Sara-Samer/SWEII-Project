package com.SWE.OnlineStorePlatform.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "user")
public class User {
	@Id
	private Long ID;
    private String email;
    private String username;
    private String password;
    private Type type;
 
    public User() {
    }
}

