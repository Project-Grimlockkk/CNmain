package com.example.rr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.learn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    Switch nightModeSwitch;
    boolean nightModeEnabled;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private FirebaseFirestore dp;

    TextView usernameTextView;
    ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile2);

        usernameTextView = findViewById(R.id.usernameTextView);
        profileImageView = findViewById(R.id.profileImageView);
        RelativeLayout aboutView = findViewById(R.id.aboutapp);
        RelativeLayout termsView = findViewById(R.id.termss);
        RelativeLayout privacyView = findViewById(R.id.privacy);
        RelativeLayout shareView = findViewById(R.id.share);
        RelativeLayout changePass = findViewById(R.id.changePass);
        RelativeLayout feedback = findViewById(R.id.feedback);
        RelativeLayout logout = findViewById(R.id.logout);
        RelativeLayout rateus = findViewById(R.id.rateus);
        AppCompatButton editProfileButton = findViewById(R.id.editProfileButton);

        dp = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        loadProfileData();

        nightModeSwitch = findViewById(R.id.nightModeSwitch1);

        // Load night mode state from SharedPreferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        nightModeEnabled = sharedPreferences.getBoolean("nightModeEnabled", true);
        nightModeSwitch.setChecked(nightModeEnabled);

        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        String savedUsername = prefs.getString("username", "");
        usernameTextView.setText(savedUsername);
        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save night mode state to SharedPreferences
                editor = sharedPreferences.edit();
                editor.putBoolean("nightModeEnabled", isChecked);
                editor.apply();

                if (isChecked) {
                    // Enable night mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    // Disable night mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EditProfileActivity when the button is clicked
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity1.class);
                startActivity(intent);
            }
        });


        aboutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AboutActivity when the button is clicked
                Intent intent = new Intent(ProfileActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        termsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the TermsActivity when the button is clicked
                Intent intent = new Intent(ProfileActivity.this, TermsActivity.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the FeedbackActivity when the button is clicked
                Intent intent = new Intent(ProfileActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });


        privacyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the PrivacyActivity when the button is clicked
                Intent intent = new Intent(ProfileActivity.this, PrivacyActivity.class);
                startActivity(intent);
            }
        });

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ForgetPasswordActivity when the button is clicked
                Intent intent = new Intent(ProfileActivity.this, RateUsActivity.class);
                startActivity(intent);
            }
        });


        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the ForgetPasswordActivity when the button is clicked
                Intent intent = new Intent(ProfileActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });


        shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain"); // Set MIME type to text/plain for sharing text
                String body = "Download This App";
                String sub = "https://www.youtube.com/watch?v=i41rmT-GDXc"; // This is the subject
                intent.putExtra(Intent.EXTRA_TEXT, body); // Set the body of the message
                intent.putExtra(Intent.EXTRA_SUBJECT, sub); // Set the subject of the message
                startActivity(Intent.createChooser(intent, "Share using: "));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        Intent intent = new Intent(ProfileActivity.this, LoginScreen.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(ProfileActivity.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, simply dismiss the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void loadProfileData() {
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
        String savedUsername = prefs.getString("username", "");
        String savedImageUri = prefs.getString("imageUri", "");

        // Set username
        usernameTextView.setText(savedUsername);

        // Load profile picture using Glide
        if (!savedImageUri.isEmpty()) {
            // Check if the URI scheme is valid
            if (savedImageUri.startsWith("content://") || savedImageUri.startsWith("file://")) {
                Glide.with(this)
                        .load(Uri.parse(savedImageUri))
                        .placeholder(R.drawable.wcesangli) // Placeholder image while loading
                        .error(R.drawable.wcesangli) // Error image if loading fails
                        .into(profileImageView);
            } else {
                // Invalid URI scheme, set default image
                profileImageView.setImageResource(R.drawable.wcesangli);
            }
        } else {
            // If no profile picture is set, display a default image
            profileImageView.setImageResource(R.drawable.wcesangli);
        }
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isAppInitialized) {
            transaction.add(R.id.frame, fragment);
        } else {
            transaction.replace(R.id.frame, fragment);
        }
        transaction.commit();
    }
}
