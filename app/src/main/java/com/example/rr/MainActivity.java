package com.example.rr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.learn.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private boolean isFirstCreate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the value of isFirstCreate from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        isFirstCreate = sharedPreferences.getBoolean("isFirstCreate", false);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        frameLayout = findViewById(R.id.frame);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            handleNavigationItemClick(item.getItemId());
            return true;
        });

        // Load the HomeFragment by default only on the first creation
        if (isFirstCreate) {
            loadFragment(new HomeFragment(), true);
            isFirstCreate = false; // Update isFirstCreate to false for subsequent launches
        }


        // Store the updated value of isFirstCreate in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirstCreate", isFirstCreate);
        editor.apply();
    }

    private void handleNavigationItemClick(int itemId) {
        if (itemId == R.id.navHome) {
            loadFragment(new HomeFragment(), false);
        } else if (itemId == R.id.navProfile) {
            loadFragment(new fragment_profile2(), false);
        } else if (itemId == R.id.navSaved) {
            loadFragment(new SavedFragment(), false);
        }
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            fragmentTransaction.add(R.id.frame, fragment);
        } else {
            fragmentTransaction.replace(R.id.frame, fragment);
        }
        fragmentTransaction.commit();
    }
}
