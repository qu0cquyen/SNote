package com.snote.Repositories;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.snote.Models.User;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class PersonalRepository {
    private static PersonalRepository instance;
    private static FirebaseDatabase mDatabase;


    private DatabaseReference mReference;
    private User user;
    private String path;



    public static PersonalRepository getInstance(){
        if(instance == null){
            instance = new PersonalRepository();
        }

        mDatabase = FirebaseDatabase.getInstance();
        return instance;
    }

    public MutableLiveData<User> setUser(String userId){
        final MutableLiveData<User> mUser = new MutableLiveData<>();
        path = userId;
        mReference = mDatabase.getReference(path);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                mUser.setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return mUser;
    }
}
