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
    @Column(name = "key")
    private int key;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    public Token(String userID, String password, int key) {
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
    }

    public String getToken() {
        return this.token;
    }

    private String generateToken(String userID, String password, int key) {
        String token = userID + "-" + password;
        String generated = "";
        for (int i = 0; i < (int) token.length(); ++i)
            generated += (char) token.charAt(i) ^ key;
        return generated;
    }

}