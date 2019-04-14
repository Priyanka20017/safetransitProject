package com.example.user.safetransitproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = auth.getCurrentUser();
                if(user==null) {
                    startActivity(new Intent(MainActivity.this, scndpg.class));
                    finish();
                }else {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                }
            }
        },3000);
    }

}
