package com.example.rr;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;

public class EditProfileActivity1 extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;
    private static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 101;
    private static final String PREFS_NAME = "MyPrefsFile";

    private ImageView profileImageView;
    private Button changePhotoButton;
    private EditText usernameEditText;
    private Button saveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileImageView = findViewById(R.id.profileImageView);
        usernameEditText = findViewById(R.id.usernameEditText);
        saveChangesButton = findViewById(R.id.saveChangesButton);

        loadSavedData();

//        changePhotoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Check if permission is grantedx
//                if (ContextCompat.checkSelfPermission(EditProfileActivity.this,
//                        Manifest.permission.READ_EXTERNAL_STORAGE)
//                        == PackageManager.PERMISSION_GRANTED) {
//                    // Permission is already granted, proceed with photo selection
//                    showPhotoSelectionDialog();
//                } else {
//                    // Permission is not granted, request it
//                    ActivityCompat.requestPermissions(EditProfileActivity.this,
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            REQUEST_PERMISSION_READ_EXTERNAL_STORAGE);
//                }
//            }
//        });

        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveChangesClicked();
            }
        });
    }

//    private void showPhotoSelectionDialog() {
//        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        pickPhotoIntent.setType("image/*");
//        startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_PICK);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_PICK && data != null) {
//            Uri selectedImageUri = data.getData();
//            profileImageView.setImageURI(selectedImageUri);
//            if (selectedImageUri != null) {
//                profileImageView.setTag(selectedImageUri.toString());
//            }
//        }
//    }

    private void onSaveChangesClicked() {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("username", usernameEditText.getText().toString());
        editor.putString("imageUri", profileImageView.getTag() != null ? profileImageView.getTag().toString() : "");
        editor.apply();

        Toast.makeText(this, "Changes saved! Please refresh this page", Toast.LENGTH_SHORT).show();
    }

    private void loadSavedData() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedUsername = prefs.getString("username", "");
        String savedImageUri = prefs.getString("imageUri", "");

        usernameEditText.setText(savedUsername);
//        if (!savedImageUri.isEmpty()) {
//            profileImageView.setImageURI(Uri.parse(savedImageUri));
//            profileImageView.setTag(savedImageUri);
//        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_PERMISSION_READ_EXTERNAL_STORAGE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                showPhotoSelectionDialog();
//            } else {
//                Toast.makeText(this, "Permission denied. Cannot access photos.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
