package com.example.rr;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learn.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackFragment extends Fragment {

    private String selectedOptions = "";

    private View rootView;

//    RadioGroup roleRadioGroup = rootView.findViewById(R.id.roleRadioGroup);
//    EditText descriptionEditText = rootView.findViewById(R.id.descriptionEditText);
//    Button submitButton = rootView.findViewById(R.id.submitButton);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feedback, container, false);

        // Initialize UI elements
        RadioGroup roleRadioGroup = rootView.findViewById(R.id.roleRadioGroup);
        EditText descriptionEditText = rootView.findViewById(R.id.descriptionEditText);
        Button submitButton = rootView.findViewById(R.id.submitButton);

        RadioButton problem1Radio = rootView.findViewById(R.id.problem1Radio);
        RadioButton problem2Radio = rootView.findViewById(R.id.problem2Radio);
        RadioButton problem3Radio = rootView.findViewById(R.id.problem3Radio);
        RadioButton problem4Radio = rootView.findViewById(R.id.problem4Radio);
        RadioButton problem5Radio = rootView.findViewById(R.id.problem5Radio);

        // Set click listener for the Submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected radio button from the radio group
                int selectedRadioButtonId = roleRadioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    // A radio button is selected
                    RadioButton selectedRadioButton = rootView.findViewById(selectedRadioButtonId);
                    String selectedRole = selectedRadioButton.getText().toString();

                    // Get description from the EditText
                    String description = descriptionEditText.getText().toString();

//                    String selectedOption = getSelectedProblemOption();
                    StringBuilder selectedOptionBuilder = new StringBuilder();

//                    selectedOptions ="";


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

                    // Remove the trailing comma and space
//                    if (selectedOptionBuilder.length() > 0) {
//                        selectedOptionBuilder.setLength(selectedOptionBuilder.length() - 2);
//                    }
//                    selectedOptions = "";
//                    selectedOptions = selectedOptionBuilder.toString();

                    // You can now handle or submit the selected role and description as needed
                    // For example, you can send them to a server, save them locally, etc.
//                    handleSubmission(selectedRole, description);

                    String submissionMessage = "Submission completed!\nRole: " + selectedRole + "\nDescription: " + description;
                    Toast.makeText(getActivity(), submissionMessage, Toast.LENGTH_SHORT).show();

//                    RadioGroup roleRadioGroup = rootView.findViewById(R.id.roleRadioGroup);
//                    int selectedRadioButtonId = roleRadioGroup.getCheckedRadioButtonId();
//                    RadioButton selectedRadioButton = rootView.findViewById(selectedRadioButtonId);
//                    String selectedOption = selectedRadioButton.getText().toString();

                    // Get a reference to the Firebase database
                    DatabaseReference feedbackRef = FirebaseDatabase.getInstance().getReference("feedback");

                    // Create a new child node under "feedback" and set the values
                    String feedbackId = feedbackRef.push().getKey();
                    Log.d("FeedbackFragment", "Feedback ID: " + feedbackId);
                    if(feedbackId!=null){
                        Feedback feedback = new Feedback(selectedRole, description, selectedOptions);
                        feedbackRef.child(feedbackId).setValue(feedback)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Data added successfully
                                        Toast.makeText(getActivity(), "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
//                                        clearInputFields();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Failed to add data
                                        Toast.makeText(getActivity(), "Failed to submit feedback. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else{
                        Toast.makeText(getActivity(), "Failed to generate feedback ID. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // No radio button is selected
                    Toast.makeText(getActivity(), "Please select a role.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

//    private String getSelectedProblemOption() {
//        String selectedOption = "";
//
//        // Get the selected problem option
//        RadioButton problem1Radio = rootView.findViewById(R.id.problem1Radio);
//        RadioButton problem2Radio = rootView.findViewById(R.id.problem2Radio);
//        RadioButton problem3Radio = rootView.findViewById(R.id.problem3Radio);
//        RadioButton problem4Radio = rootView.findViewById(R.id.problem4Radio);
//        RadioButton problem5Radio = rootView.findViewById(R.id.problem5Radio);
//
//        if (problem1Radio.isChecked()) {
//            selectedOption = problem1Radio.getText().toString();
//        } else if (problem2Radio.isChecked()) {
//            selectedOption = problem2Radio.getText().toString();
//        } else if (problem3Radio.isChecked()) {
//            selectedOption = problem3Radio.getText().toString();
//        } else if (problem4Radio.isChecked()) {
//            selectedOption = problem4Radio.getText().toString();
//        } else if (problem5Radio.isChecked()) {
//            selectedOption = problem5Radio.getText().toString();
//        }
//
//        return selectedOption;
//    }

    private void handleSubmission(String selectedRole, String description) {
        // Implement your logic for submitting the data here
        // This could be sending the data to a server, saving it locally, etc.

        // For now, let's display a toast message for demonstration purposes
//        String submissionMessage = "Submission completed!\nRole: " + selectedRole + "\nDescription: " + description;
//        Toast.makeText(getActivity(), submissionMessage, Toast.LENGTH_SHORT).show();
//
//        RadioGroup roleRadioGroup = rootView.findViewById(R.id.roleRadioGroup);
//        int selectedRadioButtonId = roleRadioGroup.getCheckedRadioButtonId();
//        RadioButton selectedRadioButton = rootView.findViewById(selectedRadioButtonId);
//        String selectedOption = selectedRadioButton.getText().toString();
//
//        // Get a reference to the Firebase database
//        DatabaseReference feedbackRef = FirebaseDatabase.getInstance().getReference("feedback");
//
//        // Create a new child node under "feedback" and set the values
//        String feedbackId = feedbackRef.push().getKey();
//        Log.d("FeedbackFragment", "Feedback ID: " + feedbackId);
//        if(feedbackId!=null){
//            Feedback feedback = new Feedback(selectedRole, description, selectedOption);
//            feedbackRef.child(feedbackId).setValue(feedback)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            // Data added successfully
//                            Toast.makeText(getActivity(), "Feedback submitted successfully!", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            // Failed to add data
//                            Toast.makeText(getActivity(), "Failed to submit feedback. Please try again.", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
//        else{
//            Toast.makeText(getActivity(), "Failed to generate feedback ID. Please try again.", Toast.LENGTH_SHORT).show();
//        }

    }
//    private void clearInputFields() {
//        descriptionEditText.setText("");
//        roleRadioGroup.clearCheck();
//        // Clear other input fields as needed
//    }
}
