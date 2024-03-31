package com.example.rr;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;

public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacypage);

        // Initialize Backbtn
        ImageView Backbtn = findViewById(R.id.backPrivacy);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for the back button
                // For example, navigate back to the previous activity
                onBackPressed(); // This will navigate back to the previous activity
            }
        });
    }
}
