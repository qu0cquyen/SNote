package com.snote.Repositories;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.snote.MainActivity;
import com.snote.Models.User;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class MainActivityRepository {
    private static MainActivityRepository instance;
    private String userID;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static MainActivityRepository getInstance(){
        if(instance == null){
            instance = new MainActivityRepository();
        }
        return instance;
    }

    public String signInWithEmailAndPassword(String userName, String password){
        firebaseAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            System.out.println("True");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            setUser(user.getUid());
                        } else {
                            setUser(null);
                        }
                    }
                }
        );
        System.out.println(getUser());
        return getUser();
    }

    private void setUser(String user_id){
        this.userID = user_id;
    }

    private String getUser(){
        return this.userID;
    }



}
