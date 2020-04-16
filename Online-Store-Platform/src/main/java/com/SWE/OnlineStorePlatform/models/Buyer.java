package com.SWE.OnlineStorePlatform.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class Buyer extends User{
	public Buyer() {
		
	}
	public Buyer(String email, String username, String password, Type type) {
		super(email, username, password, type);
	}
}
