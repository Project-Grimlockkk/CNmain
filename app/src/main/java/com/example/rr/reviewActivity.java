package com.example.rr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.learn.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class reviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Spinner spinner = findViewById(R.id.spinnerwelcome);

        // Create ArrayAdapter from resource
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinner_Apartments, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to Spinner
        spinner.setAdapter(adapter2);

        // Set OnItemSelectedListener to Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle spinner item selection
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Perform actions based on selected item
                // For example, you can call a method to filter data
                // filterData(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                submitReview();
                Spinner spinner = findViewById(R.id.spinnerwelcome);
                String selectedApartment = spinner.getSelectedItem().toString();

                // Get the review details from the TextInputEditText
                TextInputEditText descriptionInput = findViewById(R.id.DescriptionInput);
                String reviewDescription = descriptionInput.getText().toString();

                // Fetch the apartment details from Firebase based on the selected name
                DatabaseReference apartmentRef = FirebaseDatabase.getInstance().getReference("PGs").child(selectedApartment);
                apartmentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Check if the apartment exists
                        if (dataSnapshot.exists()) {
                            // Check if the "reviews" node exists under the apartment node
                            if (dataSnapshot.child("reviews").exists()) {
                                // Add the review details under the existing "reviews" node
                                apartmentRef.child("reviews").push().setValue(reviewDescription);
                            } else {
                                // Create the "reviews" node and add the review details
                                apartmentRef.child("reviews").push().setValue(reviewDescription);
                            }
                            Toast.makeText(reviewActivity.this, "Review submitted successfully", Toast.LENGTH_SHORT).show();
                            // Clear the review input field
                            descriptionInput.setText("");
                        } else {
                            // Handle the case where the apartment doesn't exist
                            // For example, display an error message to the user
                            Toast.makeText(reviewActivity.this, "Apartment not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle onCancelled event
                        Toast.makeText(reviewActivity.this, "Failed to submit review: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}