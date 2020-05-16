package com.snote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.snote.Models.User;
import com.snote.ViewModels.MainActivityViewModels;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private Button mBtnLogin;
    private TextView mUserName;
    private TextView mPassword;
    private MainActivityViewModels mMainActivityViewModels;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser mCurrentUser = firebaseAuth.getCurrentUser();

    String userID;

    private void appLogin(String userName, String password){
        if(userName.length() == 0 && password.length() == 0){
            Toast.makeText(getApplicationContext(), "User name and Password are incorrect", Toast.LENGTH_LONG).show();

        } else {
            mMainActivityViewModels.signIn(this, userName, password);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.txt_userName);
        mPassword = findViewById(R.id.txt_password);
        mBtnLogin = findViewById(R.id.btn_login);

        mMainActivityViewModels = new ViewModelProvider(this).get(MainActivityViewModels.class);
        mMainActivityViewModels.init();

        mCurrentUser = firebaseAuth.getCurrentUser();



        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*userID = "T522sP2d3bccgirSZtwpLaVveSh2";
                Intent homeIntent = new Intent(MainActivity.this, Home.class);
                homeIntent.putExtra("UID", userID);
                startActivity(homeIntent);*/

                /*Intent homeIntent = new Intent(MainActivity.this, Home.class);
                startActivity(homeIntent);*/
                /*String userName = mUserName.getText().toString();
                String password = mPassword.getText().toString();
                String userID = null;*/

                //userID = appLogin(mUserName.getText().toString(), mPassword.getText().toString());


                appLogin(mUserName.getText().toString(), mPassword.getText().toString());

                /*if(userName.length() == 0 && password.length() == 0){
                    Toast.makeText(getApplicationContext(), "User name and Password are incorrect", Toast.LENGTH_LONG).show();
                } else {
                    String userID = mMainActivityViewModels.signIn(userName, password);
                    if(mMainActivityViewModels.signIn(userName, password) == null){
                        Toast.makeText(getApplicationContext(), "User name and Password are incorrect", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent homeIntent = new Intent(MainActivity.this, Home.class);
                        homeIntent.putExtra("userID", userID);
                        startActivity(homeIntent);
                    }
                }*/
            }
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(this);

    }

    @Override
    protected void onStop(){
        super.onStop();
        firebaseAuth.signOut();

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(firebaseAuth.getCurrentUser() != null){
            userID = firebaseAuth.getCurrentUser().getUid();
            Intent homeIntent = new Intent(MainActivity.this, Home.class);
            homeIntent.putExtra("UID", userID);
            startActivity(homeIntent);
        }
    }
}
