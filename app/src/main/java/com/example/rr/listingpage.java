package com.example.rr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learn.R;

import java.util.ArrayList;

public class listingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listingpage);

        AppCompatButton addingPGButton = findViewById(R.id.addingPG);
        addingPGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load InputActivity fragment
                loadInputActivityFragment();
            }
        });
    }
    private void loadInputActivityFragment() {
        // Create an instance of the InputActivity fragment
        Fragment inputActivityFragment = new InputActivity();

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the fragment_container with the inputActivityFragment
        fragmentTransaction.replace(R.id.homepage, inputActivityFragment);

        // Add the transaction to the back stack
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }
}
