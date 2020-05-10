package com.snote.ViewModels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.snote.Repositories.MainActivityRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModels extends ViewModel {
    private MutableLiveData<String> mUserId;
    private MainActivityRepository mMainActivityRepo;

    public void init(){
        mMainActivityRepo = MainActivityRepository.getInstance();
    }

    public void signIn(String user_name, String pass){
        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(user_name, pass);
        asyncTaskRunner.execute();
    }

    public MutableLiveData<String> getUserID(){
        return mUserId;
    }

    @SuppressLint("StaticFieldLeak")
    class AsyncTaskRunner extends AsyncTask<String, String, String>{
        private String userName;
        private String password;
        private String userId;

        AsyncTaskRunner(String user_name, String pass){
            this.userName = user_name;
            this.password = pass;
        }

        @Override
        protected void onPostExecute(String aVoid){
            super.onPostExecute(aVoid);
            userId = mMainActivityRepo.signInWithEmailAndPassword(userName, password);
            mUserId.postValue(userId);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }







}
