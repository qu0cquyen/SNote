package com.snote.Models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String userName;
    private String profilePic;
    private String school;
    private String dob;
    private Friends friends;
    private Achievements achievements;
    private Features features;
    private Notes notes;

    public User(){}

    public User(String user_name, String profile_pic, String sch, String DoB, Friends friend, Achievements achi, Features f, Notes note){
        this.userName = user_name;
        this.profilePic = profile_pic;
        this.school = sch;
        this.dob = DoB;
        this.friends = friend;
        this.achievements = achi;
        this.features = f;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDoB() {
        return dob;
    }

    public void setDoB(String dob) {
        this.dob = dob;
    }

    public Achievements getAchievements() {
        return achievements;
    }

    public void setAchievements(Achievements achievements) {
        this.achievements = achievements;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features pFeatures) {
        this.features = pFeatures;
    }
}
