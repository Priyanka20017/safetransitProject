package com.example.user.safetransitproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminLoginActivity extends AppCompatActivity {
    EditText id,pass;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().hide();
        id = findViewById(R.id.adminId);
        pass = findViewById(R.id.adminPass);
        btn = findViewById(R.id.adminLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useerId = id.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if(TextUtils.isEmpty(useerId)){
                    id.setError("Please enter id.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    pass.setError("Please enter password");
                    return;
                }
                if(useerId.equals("admin")){
                    if(password.equals("priyanka")){
                        startActivity(new Intent(AdminLoginActivity.this,AdminHomeActivity.class));
                        finish();
                    }else{
                        pass.setError("Incorrect Password");
                    }
                }else{
                    id.setError("Incorrect Id");
                }

            }
        });
    }
}
