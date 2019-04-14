package com.example.user.safetransitproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    Button button1, button2;
    EditText editText1, editText2, editText;
    private FirebaseAuth mAuth;
    String TAG = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth=FirebaseAuth.getInstance();
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText= findViewById(R.id.editText);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editText1.getText().toString();
                String email=editText2.getText().toString();
                String pass=editText.getText().toString();
               seas(email,pass);

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,scndpg.class));
            }
        });
    }

 private void seas(String email, String password){

      mAuth.createUserWithEmailAndPassword(email, password)
              .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
                          // Sign in success, update UI with the signed-in user's information
                          Log.d(TAG, "createUserWithEmail:success");
                          FirebaseUser user = mAuth.getCurrentUser();
                          Toast.makeText(signup.this, "user signup", Toast.LENGTH_SHORT).show();

                      } else {
                          // If sign in fails, display a message to the user.
                          Log.w(TAG, "createUserWithEmail:failure", task.getException());
                          Toast.makeText(signup.this, "Authentication failed.",
                                  Toast.LENGTH_SHORT).show();

                      }

                      // ...
                  }
              });

  }


    }

