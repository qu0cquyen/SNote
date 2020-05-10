package com.snote.Fragments;


import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
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

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.snote.Adapters.PersonalAdapter;
import com.snote.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {

    private ImageView mImageBanner;
    private ImageView mImageBurger;
    private ImageView mImageProfile;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private RelativeLayout mPersonalContainer;

    private float lastTranslate = 0.0f;

    public PersonalFragment() {
        // Required empty public constructor
    }

    private void init_RecyclerView(View rootView){
       List<String> lstFeatures = new ArrayList<>();
       lstFeatures.add("Notes");
       lstFeatures.add("Friends");
       lstFeatures.add("Achievement");


        RecyclerView mRecyclerView = rootView.findViewById(R.id.features_recyclerView);

        PersonalAdapter mPersonalAdapter = new PersonalAdapter(rootView.getContext(), lstFeatures);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(rootView.getContext(), LinearLayout.VERTICAL));
        mRecyclerView.setAdapter(mPersonalAdapter);

    }

    private void init(View rootView){
        mImageBanner = rootView.findViewById(R.id.banner_image);
        mImageBurger = rootView.findViewById(R.id.banner_burgerMenu);
        mImageProfile = rootView.findViewById(R.id.personal_image);
        mPersonalContainer = rootView.findViewById(R.id.personal_container);
        mNavigationView = rootView.findViewById(R.id.personal_navView);
        mDrawerLayout = rootView.findViewById(R.id.personal_drawerLayout);

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerSlide(View drawerView, float slideOffset){
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = (mNavigationView.getWidth() * slideOffset) * -1;

                TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                anim.setDuration(0);
                anim.setFillAfter(true);
                mPersonalContainer.startAnimation(anim);

                lastTranslate = moveFactor;

            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void imageInit(View rootView){
        Glide.with(rootView.getContext())
                .load(R.drawable.cat_banner)
                .into(mImageBanner);

        Glide.with(rootView.getContext())
                .load(R.drawable.ic_burger)
                .override(60, 60)
                .circleCrop()
                .into(mImageBurger);

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

         init_RecyclerView(rootView);
         init(rootView);
         imageInit(rootView);

         mImageBurger.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mDrawerLayout.openDrawer(Gravity.RIGHT);
             }
         });



         return rootView;
    }

}
