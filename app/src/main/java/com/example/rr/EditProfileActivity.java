package com.example.rr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;

public class EditProfileActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;
    private static final String PREFS_NAME = "MyPrefsFile";

    private ImageView profileImageView;
    private Button changePhotoButton;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private Button saveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileImageView = findViewById(R.id.profileImageView);
        changePhotoButton = findViewById(R.id.changePhotoButton);
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText1);
        phoneEditText = findViewById(R.id.phoneEditText1);
        saveChangesButton = findViewById(R.id.saveChangesButton);

        loadSavedData();

        changePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show options for selecting a photo from gallery
                showPhotoSelectionDialog();
            }
        });

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Save Changes button click
                onSaveChangesClicked();
            }
        });
    }



    // Method to show a dialog for selecting a photo from gallery
    private void showPhotoSelectionDialog() {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhotoIntent.setType("image/*");
        startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_PICK);
    }

    // Method to handle the result of photo selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_PICK && data != null) {
            // Get the selected image URI from gallery
            Uri selectedImageUri = data.getData();
            profileImageView.setImageURI(selectedImageUri);
            if (selectedImageUri != null) {
                profileImageView.setTag(selectedImageUri.toString());
            }
        }
    }

    // Method to handle Save Changes button click
    private void onSaveChangesClicked() {
        // Save the entered data in SharedPreferences
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("username", usernameEditText.getText().toString());
        editor.putString("email", emailEditText.getText().toString());
        editor.putString("phone", phoneEditText.getText().toString());
        editor.putString("imageUri", profileImageView.getTag().toString());
        editor.apply();

        Intent intent = new Intent(EditProfileActivity.this, fragment_profile2.class);
        intent.putExtra("username", usernameEditText.getText().toString());
        startActivity(intent);


        // Show a toast message to indicate that the changes are saved
        Toast.makeText(this, "Changes saved!", Toast.LENGTH_SHORT).show();
    }



    // Method to load saved data from SharedPreferences and populate EditText fields
    private void loadSavedData() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedUsername = prefs.getString("username", "");
        String savedEmail = prefs.getString("email", "");
        String savedPhone = prefs.getString("phone", "");

        // Populate EditText fields with saved data
        usernameEditText.setText(savedUsername);
        emailEditText.setText(savedEmail);
        phoneEditText.setText(savedPhone);
    }
}
