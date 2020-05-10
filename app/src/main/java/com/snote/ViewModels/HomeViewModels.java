package com.snote.ViewModels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.snote.Models.Content;
import com.snote.Repositories.ContentRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModels extends ViewModel {
    private MutableLiveData<List<Content>> mContents;
    private ContentRepository mContentRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mContents != null){
            return;
        }
        mContentRepo = ContentRepository.getInstance();
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
