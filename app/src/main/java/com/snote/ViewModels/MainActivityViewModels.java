package com.snote.ViewModels;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;

import com.snote.MainActivity;
import com.snote.Repositories.MainActivityRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModels extends ViewModel {
    private MainActivityRepository mMainActivityRepo;

    public void init(){
        mMainActivityRepo = MainActivityRepository.getInstance();
    }

    public void signIn(Activity activity, String user_name, String pass){
        mMainActivityRepo.signInWithEmailAndPassword(activity, user_name, pass);

    }
}
