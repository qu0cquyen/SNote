package com.snote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.snote.Fragments.ContentAddFragment;
import com.snote.Fragments.HomeFragment;
import com.snote.Fragments.MessageFragment;
import com.snote.Fragments.PersonalFragment;
import com.snote.Fragments.SubscribeFragment;
import com.snote.Models.User;
import com.snote.ViewModels.HomeViewModels;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private String userID;

    private ImageView mHomeImageView;
    private DrawerLayout mHomeDrawerLayout;
    private NavigationView mHomeNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mHomeRelativeLayout;

    private BottomNavigationView mBtmNav;
    private FragmentManager fm = getSupportFragmentManager();

    private float lastTranslate = 0.0f;

    private HomeViewModels mHomeViewModels;
    private User mUser;


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Bundle bundle = new Bundle();
                    mHomeImageView.setVisibility(View.GONE);
                    Fragment selectedFragment = null;
                    switch(menuItem.getItemId()){
                           case R.id.nav_home:
                               selectedFragment = new HomeFragment();
                               break;

                           case R.id.nav_fav:
                               selectedFragment = new SubscribeFragment();
                               break;

                           case R.id.nav_add:
                               selectedFragment = new ContentAddFragment();
                               break;

                           case R.id.nav_message:
                               selectedFragment = new MessageFragment();
                               break;

                           case R.id.nav_personal:
                               selectedFragment = new PersonalFragment();
                               if(mUser != null){
                                   bundle.putString("userName", mUser.getUserName());
                                   bundle.putString("DoB", mUser.getDoB());
                                   bundle.putString("school", mUser.getSchool());
                                   bundle.putStringArrayList("listFeatures", (ArrayList<String>) mUser.getFeatures().getListFeatures());

                                   System.out.println(mUser.getFriends());
                               }
                               selectedFragment.setArguments(bundle);

                               mHomeImageView.setVisibility(View.VISIBLE);

                               Glide.with(getApplicationContext())
                                       .load(R.drawable.ic_burger)
                                       .override(60, 60)
                                       .circleCrop()
                                       .into(mHomeImageView);

                               mHomeImageView.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       mHomeDrawerLayout.openDrawer(Gravity.RIGHT);
                                   }
                               });
                               break;
                    }

                    fm.beginTransaction().replace(R.id.frame_container, selectedFragment).commit();

                    return true;
                }
            };

    private void init(){
        mHomeViewModels = new ViewModelProvider(this).get(HomeViewModels.class);
        mHomeViewModels.init();

        mHomeImageView = findViewById(R.id.home_burgerImage);
        mHomeDrawerLayout = findViewById(R.id.home_drawerLayout);
        mHomeNavigationView = findViewById(R.id.home_navView);
        mHomeRelativeLayout = findViewById(R.id.home_Container);
        mDrawerToggle = new ActionBarDrawerToggle(this, mHomeDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerSlide(View drawerView, float slideOffset){
                // Move all the UI to the left when opening drawer
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = mHomeNavigationView.getWidth() * slideOffset * -1;

                TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                anim.setDuration(0);
                anim.setFillAfter(true);
                mHomeRelativeLayout.startAnimation(anim);

                lastTranslate = moveFactor;
            }
        };

        mHomeDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mHomeImageView.setVisibility(View.GONE);

        mBtmNav = findViewById(R.id.navigation_view);
        mBtmNav.setOnNavigationItemSelectedListener(navListener);
        mBtmNav.setItemIconTintList(null);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        fm.beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

        Bundle extras = getIntent().getExtras();
        userID = extras.getString("UID");

        mHomeViewModels.getUserInfo(userID).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mUser = user;
            }
        });

    }
}
