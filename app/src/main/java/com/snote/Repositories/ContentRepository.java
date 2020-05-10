package com.snote.Repositories;

import com.snote.Models.Content;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * Singleton Pattern
 */
public class ContentRepository {
    private static ContentRepository instance;
    private ArrayList<Content> dataSet = new ArrayList<>();

    public static ContentRepository getInstance(){
        if(instance == null){
            instance = new ContentRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public MutableLiveData<List<Content>> getContents(){
        setContents();

        MutableLiveData<List<Content>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setContents(){
        dataSet.add(new Content("Title A", "Author A", "This is description for Title A"));
        dataSet.add(new Content("Title B", "Author B", "This is description for Title B"));
        dataSet.add(new Content("Title C", "Author C", "This is description for Title C"));
        dataSet.add(new Content("Title D", "Author D", "This is description for Title D"));
        dataSet.add(new Content("Title E", "Author E", "This is description for Title E"));
    }
}
