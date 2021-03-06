package com.SWE.OnlineStorePlatform.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_token")
public class Token {
    @Id
    @Column(name = "token", unique = true)
    private String token;
    @Column(name = "start_date")
    private Timestamp startDate;
    @Column(name = "end_date")
    private Timestamp endDate;
    @Column(name = "encryption_key")
    private int key;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    public Token() {
        this.key = 0;
        this.token = "";
        this.startDate = new Timestamp(0);
        this.endDate = new Timestamp(0);
        this.type = Type.BUYER;
    }

    public Token(String userID, String password, Character key, Type type) {
        this.key = key;
        this.token = this.generateToken(userID, password, key);
        Date date = new Date();
        long time = date.getTime();
        this.startDate = new Timestamp(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 15);
        time = cal.getTime().getTime();
        this.endDate = new Timestamp(time);
        this.type = type;
    }

    public String getToken() {
        return this.token;
    }

    public Timestamp getStartDate() {
        return this.startDate;
    }

    public Timestamp getEndDate() {
        return this.endDate;
    }

    public Type getType() {
        return this.type;
    }

    public Boolean isValid() {
        Date date = new Date();
        long time = date.getTime();
        Timestamp currentDate = new Timestamp(time);
        return (currentDate.after(this.startDate) && currentDate.before(this.endDate));
    }

    private String generateToken(String userID, String password, Character key) {
        String token = userID + "-" + password;
        String generated = "";
        for (int i = 0; i < (int) token.length(); ++i)
            generated += (char) token.charAt(i) ^ key;
        return generated;
    }

}