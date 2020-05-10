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
    private String userID = null;
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
                            userID = user.getUid();
                        }
                    }
                }
        );
        return userID;
    }





}
