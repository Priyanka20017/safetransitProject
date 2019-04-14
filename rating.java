package com.example.user.safetransitproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class rating extends AppCompatActivity {
    RatingBar ratingBar;
    Button rat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);


        addListtenerOnButtonClick();
    }
    public  void addListtenerOnButtonClick(){
        ratingBar = findViewById(R.id.ratingBar);
        rat = findViewById(R.id.rat);
        rat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(),rating, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
