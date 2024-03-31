package com.example.rr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    private String selectedOptions = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        // Initialize UI elements
        RadioGroup roleRadioGroup = findViewById(R.id.roleRadioGroup);
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        Button submitButton = findViewById(R.id.submitButton);
        ImageView backButton = findViewById(R.id.backButton);

        RadioButton problem1Radio = findViewById(R.id.problem1Radio);
        RadioButton problem2Radio = findViewById(R.id.problem2Radio);
        RadioButton problem3Radio = findViewById(R.id.problem3Radio);
        RadioButton problem4Radio = findViewById(R.id.problem4Radio);
        RadioButton problem5Radio = findViewById(R.id.problem5Radio);

        // Set click listener for the Submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected radio button from the radio group
                int selectedRadioButtonId = roleRadioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    // A radio button is selected
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedRole = selectedRadioButton.getText().toString();

                    // Get description from the EditText
                    String description = descriptionEditText.getText().toString();

                    // Reset selectedOptions
                    selectedOptions = "";

                    if (problem1Radio.isChecked()) {
                        selectedOptions += problem1Radio.getText().toString() + ", ";
                    }
                    if (problem2Radio.isChecked()) {
                        selectedOptions += problem2Radio.getText().toString() + ", ";
                    }
                    if (problem3Radio.isChecked()) {
                        selectedOptions += problem3Radio.getText().toString() + ", ";
                    }
                    if (problem4Radio.isChecked()) {
                        selectedOptions += problem4Radio.getText().toString() + ", ";
                    }
                    if (problem5Radio.isChecked()) {
                        selectedOptions += problem5Radio.getText().toString() + ", ";
                    }

                    // You can now handle or submit the selected role and description as needed
                    // For example, you can send them to a server, save them locally, etc.
                    handleSubmission(selectedRole, description);
                } else {
                    // No radio button is selected
                    Toast.makeText(FeedbackActivity.this, "Please select a role.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the main page using an Intent
                Intent intent = new Intent(FeedbackActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleSubmission(String selectedRole, String description) {
        // Get a reference to the Firebase database
        DatabaseReference feedbackRef = FirebaseDatabase.getInstance().getReference("feedback");

        // Create a new child node under "feedback" and set the values
        String feedbackId = feedbackRef.push().getKey();
        Log.d("FeedbackActivity", "Feedback ID: " + feedbackId);
        if (feedbackId != null) {
            Feedback feedback = new Feedback(selectedRole, description, selectedOptions);
            feedbackRef.child(feedbackId).setValue(feedback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Data added successfully
                            Toast.makeText(FeedbackActivity.this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to add data
                            Toast.makeText(FeedbackActivity.this, "Failed to submit feedback. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(FeedbackActivity.this, "Failed to generate feedback ID. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
