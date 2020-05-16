package com.snote.Repositories;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.snote.Models.Content;
import com.snote.Models.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

/**
 * Singleton Pattern
 */
public class HomeRepository {
    private static HomeRepository instance;
    private ArrayList<Content> dataSet = new ArrayList<>();

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mReference;

    public static HomeRepository getInstance(){
        if(instance == null){
            instance = new HomeRepository();
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

    public MutableLiveData<User> getUserInfo(String uid){
        final MutableLiveData<User> mUser = new MutableLiveData<>();
        String path = uid;
        mReference = mDatabase.getReference(path);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mUser.setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return mUser;
    }
}
