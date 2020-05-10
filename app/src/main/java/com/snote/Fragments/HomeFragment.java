package com.snote.Fragments;


import androidx.fragment.app.Fragment;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.snote.Adapters.HomeAdapter;

import com.snote.Models.Content;
import com.snote.R;
import com.snote.ViewModels.HomeViewModels;

import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private HomeAdapter contentAdapter;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mPullToRefresh;

    // ViewModels
    private HomeViewModels mHomeViewModel;


    public HomeFragment() {
        // Required empty public constructor
    }

    private void Recycler_init(View rootView){
        mHomeViewModel = new ViewModelProvider(this).get(HomeViewModels.class);
        mHomeViewModel.init();

        mRecyclerView = rootView.findViewById(R.id.home_recyclerView);

        contentAdapter = new HomeAdapter(rootView.getContext(), mHomeViewModel.getContents().getValue());

        // Load data from the adapter
        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        mRecyclerView.setAdapter(contentAdapter);

    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mProgressBar = rootView.findViewById(R.id.progressBar);
        mPullToRefresh = rootView.findViewById(R.id.pullToRefresh);

        Recycler_init(rootView);

        // View Model
        // Observe data change through Live Data
        mHomeViewModel.getContents().observe(getViewLifecycleOwner(), new Observer<List<Content>>() {
            @Override
            public void onChanged(List<Content> contents) {
                contentAdapter.notifyDataSetChanged();
            }
        });

        mHomeViewModel.getIsUpdating().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    //Show Progress Bar
                    showProgressBar();
                }
                else{
                    //Hide Progress Bar
                    hideProgressBar();
                    mRecyclerView.smoothScrollToPosition(mHomeViewModel.getContents().getValue().size()-1);
                }
            }
        });

        mPullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHomeViewModel.addNewValue(new Content("Title G", "Author G", "This is a description for Title G"));
                mPullToRefresh.setRefreshing(false);
            }
        });

        return rootView;
    }

}
