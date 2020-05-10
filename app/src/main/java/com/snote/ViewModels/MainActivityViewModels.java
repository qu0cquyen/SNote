package com.snote.ViewModels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.snote.Repositories.MainActivityRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModels extends ViewModel {
    private String mUserId;
    private MainActivityRepository mMainActivityRepo;

    public void init(){
        mMainActivityRepo = MainActivityRepository.getInstance();
    }

    public String signIn(String user_name, String pass){
        this.mUserId = mMainActivityRepo.signInWithEmailAndPassword(user_name, pass);
        return mUserId;
    }









}
