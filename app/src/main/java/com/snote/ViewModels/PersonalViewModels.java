package com.snote.ViewModels;

import com.snote.Models.User;
import com.snote.Repositories.PersonalRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonalViewModels extends ViewModel {
    private PersonalRepository mPersonalRepository;
    private MutableLiveData<User> mUser;

    public void init(){
        if(mUser != null){
            return;
        }
        mPersonalRepository = PersonalRepository.getInstance();
    }

    public LiveData<User> setUser(String uid){
        return mPersonalRepository.setUser(uid);
    }

}
