package com.snote.Models;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String profilePic;
    private Friends friends;
    private Notes notes;

    public User(String user_name, String profile_pic, Friends friend, Notes note){
        this.userName = user_name;
        this.profilePic = profile_pic;
        this.friends = friend;
        this.notes = note;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
}
