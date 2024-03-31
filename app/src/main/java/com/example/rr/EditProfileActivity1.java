package com.example.rr;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;

public class EditProfileActivity1 extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";

    private ImageView profileImageView;
    private EditText usernameEditText;
    private Button saveChangesButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileImageView = findViewById(R.id.profileImageView);
        usernameEditText = findViewById(R.id.usernameEditText);
        saveChangesButton = findViewById(R.id.saveChangesButton);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing ...");
        progressDialog.setCancelable(false);

        loadSavedData();

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveChangesClicked();
            }
        });
    }

    private void onSaveChangesClicked() {
        progressDialog.show();
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("username", usernameEditText.getText().toString());
        editor.putString("imageUri", profileImageView.getTag() != null ? profileImageView.getTag().toString() : "");
        editor.apply();

        // Simulate delay for refreshing profile
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity1.this, "Profile Saved!", Toast.LENGTH_SHORT).show();
            }
        }, 2000); // 2000 milliseconds (2 seconds)
    }

    private void loadSavedData() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedUsername = prefs.getString("username", "");

        usernameEditText.setText(savedUsername);
    }
}
