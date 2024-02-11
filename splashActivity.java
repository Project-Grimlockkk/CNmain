package com.example.roomradar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView giff = findViewById(R.id.giff);

        Glide.with(this) .load(R.drawable.roomradar).into(giff);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iHome = new Intent(splashActivity.this, MainActivity.class);
                startActivity(iHome);
            }
        },4000);

    }
}