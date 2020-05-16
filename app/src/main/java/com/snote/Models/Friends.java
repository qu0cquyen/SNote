package com.snote.Models;

import com.google.firebase.database.PropertyName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Friends implements Serializable {
    private String userID;
    private String userName;
    private String date;

    public Friends(){}

    public Friends(String uName, String d){
        this.userName = uName;
        this.date = d;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
