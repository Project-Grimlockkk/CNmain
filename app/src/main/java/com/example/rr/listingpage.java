package com.example.rr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;

public class listingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listingpage);

        Button addingPGButton = findViewById(R.id.addingPG);

        addingPGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the InputActivity
                Intent intent = new Intent(listingpage.this, InputActivity.class);
                startActivity(intent);
            }
        });
    }
}
