package com.snote.Fragments;


import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.snote.Adapters.PersonalAdapter;
import com.snote.Models.User;
import com.snote.R;
import com.snote.ViewModels.PersonalViewModels;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {

    private TextView mPersonalName;
    private TextView mPersonalDoB;
    private TextView mPersonalSchool;
    private ImageView mImageBanner;
    private ImageView mImageProfile;

    private PersonalViewModels mPersonalViewModel;

    private String mUserName;
    private String DoB;
    private String mSchool;
    private List<String> listFeatures = new ArrayList<>();

    Bundle bundle;



    public PersonalFragment() {
        // Required empty public constructor
    }

    private void init_RecyclerView(View rootView){

        if(listFeatures != null){
            RecyclerView mRecyclerView = rootView.findViewById(R.id.features_recyclerView);

            PersonalAdapter mPersonalAdapter = new PersonalAdapter(rootView.getContext(), listFeatures);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
            mRecyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(), LinearLayout.VERTICAL));
            mRecyclerView.setAdapter(mPersonalAdapter);
        }


    }

    private void init(View rootView){
        mPersonalViewModel = new ViewModelProvider(this).get(PersonalViewModels.class);
        mPersonalViewModel.init();

        mPersonalName = rootView.findViewById(R.id.personal_txtUserName);
        mPersonalSchool = rootView.findViewById(R.id.personal_txtSchool);
        mPersonalDoB = rootView.findViewById(R.id.personal_txtDoB);
        mImageBanner = rootView.findViewById(R.id.banner_image);
        mImageProfile = rootView.findViewById(R.id.personal_image);


    }

    private void imageInit(View rootView){
        Glide.with(rootView.getContext())
                .load(R.drawable.cat_banner)
                .into(mImageBanner);

        Glide.with(this)
                .load(R.drawable.profile_img)
                .circleCrop()
                .into(mImageProfile);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View rootView = inflater.inflate(R.layout.fragment_personal, container, false);

        init(rootView);

        // Set values to interfaces
        bundle = this.getArguments();
        if(bundle != null){
            mPersonalName.setText(bundle.getString("userName"));
            mPersonalDoB.setText(bundle.getString("DoB"));
            mPersonalSchool.setText(bundle.getString("school"));
            listFeatures = bundle.getStringArrayList("listFeatures");
            System.out.println(listFeatures);
        }

         init_RecyclerView(rootView);

         imageInit(rootView);





         /*mPersonalViewModel.setUser(userID).observe(getViewLifecycleOwner(), new Observer<User>() {
             @Override
             public void onChanged(User user) {
                 mPersonalName.setText(user.getUserName());
                 mPersonalSchool.setText(user.getSchool());
                 mPersonalDoB.setText(user.getDoB());
                 listFeatures = user.getFeatures().getListFeatures();

             }
         });*/


         //mPersonalName.setText(mPersonalViewModel.getUser().getValue());

         /*mPersonalName.setText(mUser.getValue().getUserName());
         mPersonalSchool.setText(mUser.getValue().getSchool());
         mPersonalDoB.setText(mUser.getValue().getDoB());*/

         return rootView;
    }

}
