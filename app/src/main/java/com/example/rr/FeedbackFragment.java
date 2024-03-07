package com.example.rr;

import android.os.Bundle;
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

public class FeedbackFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feedback, container, false);

        // Initialize UI elements
        RadioGroup roleRadioGroup = rootView.findViewById(R.id.roleRadioGroup);
        EditText descriptionEditText = rootView.findViewById(R.id.descriptionEditText);
        Button submitButton = rootView.findViewById(R.id.submitButton);

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

                    // You can now handle or submit the selected role and description as needed
                    // For example, you can send them to a server, save them locally, etc.
                    handleSubmission(selectedRole, description);
                } else {
                    // No radio button is selected
                    Toast.makeText(getActivity(), "Please select a role.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    private void handleSubmission(String selectedRole, String description) {
        // Implement your logic for submitting the data here
        // This could be sending the data to a server, saving it locally, etc.

        // For now, let's display a toast message for demonstration purposes
        String submissionMessage = "Submission completed!\nRole: " + selectedRole + "\nDescription: " + description;
        Toast.makeText(getActivity(), submissionMessage, Toast.LENGTH_SHORT).show();
    }
}
