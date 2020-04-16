package com.SWE.OnlineStorePlatform.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class Owner extends User{
	public Owner() {
		
	}
	public Owner(String email, String username, String password, Type type) {
		super(email, username, password, type);
	}
}
