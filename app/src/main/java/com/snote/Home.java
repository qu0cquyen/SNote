package com.snote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Message;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.snote.Fragments.ContentAddFragment;
import com.snote.Fragments.HomeFragment;
import com.snote.Fragments.MessageFragment;
import com.snote.Fragments.PersonalFragment;
import com.snote.Fragments.SubscribeFragment;

public class Home extends AppCompatActivity {

    BottomNavigationView mBtmNav;
    FragmentManager fm = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                           break;
                   }

                   fm.beginTransaction().replace(R.id.frame_container, selectedFragment).commit();

                   return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mBtmNav = findViewById(R.id.navigation_view);
        mBtmNav.setOnNavigationItemSelectedListener(navListener);
        mBtmNav.setItemIconTintList(null);

        fm.beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

    }
}
