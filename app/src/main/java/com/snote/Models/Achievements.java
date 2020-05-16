package com.snote.Models;

import java.util.HashMap;
import java.util.List;

public class Achievements {
    private HashMap<String, String> achid;

    public Achievements(){}

    public Achievements(HashMap<String, String> achID){
        this.achid = achID;
    }

    public HashMap<String, String> getAchid() {
        return achid;
    }

    public void setAchid(HashMap<String, String> achid) {
        this.achid = achid;
    }
}
