package com.snote.ViewModels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.snote.Models.Content;
import com.snote.Models.User;
import com.snote.Repositories.HomeRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModels extends ViewModel {
    private MutableLiveData<List<Content>> mContents;
    private HomeRepository mContentRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mContents != null){
            return;
        }
        mContentRepo = HomeRepository.getInstance();
        mContents = mContentRepo.getContents();
    }

    public void addNewValue(Content content){
        mIsUpdating.setValue(true);

        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(content);
        asyncTaskRunner.execute();
    }

    public LiveData<List<Content>> getContents(){
        return mContents;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }

    public LiveData<User> getUserInfo(String uid){
        MutableLiveData<User> mUserInfo = mContentRepo.getUserInfo(uid);

        return mUserInfo;
    }


    @SuppressLint("StaticFieldLeak")
    class AsyncTaskRunner extends AsyncTask<Content, Content, Content>{
        private Content content;

        AsyncTaskRunner(Content c){
            this.content = c;
        }

        @Override
        protected Content doInBackground(Content... contents) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Content aVoid){
            super.onPostExecute(aVoid);
            List<Content> currentContents = mContents.getValue();
            currentContents.add(content);
            mContents.postValue(currentContents);
            mIsUpdating.setValue(false);
        }
    }
}
