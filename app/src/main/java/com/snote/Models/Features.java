package com.snote.Models;

import java.util.ArrayList;
import java.util.List;

public class Features {
    private boolean achievements;
    private boolean yourNotes;

    public Features(){}

   public Features(boolean achi, boolean yNotes){
        this.achievements = achi;
        this.yourNotes = yNotes;
   }

    public List<String> getListFeatures(){
        List<String> pListFeatures = new ArrayList<>();

        if(achievements){pListFeatures.add("Achievements");}
        if(yourNotes){pListFeatures.add("Your Notes");}

        return pListFeatures;
    }

    public boolean isYourNotes() {
        return yourNotes;
    }

    public void setYourNotes(boolean yourNotes) {
        this.yourNotes = yourNotes;
    }

    public boolean isAchievements() {
        return achievements;
    }

    public void setAchievements(boolean achievements) {
        this.achievements = achievements;
    }
}
