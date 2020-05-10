package com.snote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.snote.ViewModels.MainActivityViewModels;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private TextView mUserName;
    private TextView mPassword;
    private MainActivityViewModels mMainActivityViewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.txt_userName);
        mPassword = findViewById(R.id.txt_password);
        mBtnLogin = findViewById(R.id.btn_login);

        mMainActivityViewModels = new ViewModelProvider(this).get(MainActivityViewModels.class);
        mMainActivityViewModels.init();

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, Home.class);
                startActivity(homeIntent);
                /*String userName = mUserName.getText().toString();
                String password = mPassword.getText().toString();

                if(userName.length() == 0 && password.length() == 0){
                    Toast.makeText(getApplicationContext(), "User name and Password are incorrect", Toast.LENGTH_LONG).show();
                } else {
                    if(mMainActivityViewModels.signIn(userName, password) == null){
                        Toast.makeText(getApplicationContext(), "User name and Password are incorrect", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent homeIntent = new Intent(MainActivity.this, Home.class);
                        startActivity(homeIntent);
                    }
                }*/
            }
        });
    }
}
