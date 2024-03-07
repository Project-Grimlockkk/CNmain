package com.example.rr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learn.R;

public class InputActivity extends Fragment {

    private EditText firstNameEditText, lastNameEditText, phoneNoEditText, addressEditText, rentInrEditText, depositInrEditText, descriptionEditText;
    private Spinner tenantTypeSpinner, noOfBedsSpinner;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.input_page, container, false);

        // Initialize views
        firstNameEditText = rootView.findViewById(R.id.firstname);
        lastNameEditText = rootView.findViewById(R.id.lastname);
        phoneNoEditText = rootView.findViewById(R.id.phoneno);
        addressEditText = rootView.findViewById(R.id.address);
        rentInrEditText = rootView.findViewById(R.id.RENTINR);
        depositInrEditText = rootView.findViewById(R.id.DEPOSITINR);
        descriptionEditText = rootView.findViewById(R.id.descriptionEditText);

        tenantTypeSpinner = rootView.findViewById(R.id.tenantTypeSpinner);
        noOfBedsSpinner = rootView.findViewById(R.id.noOfBedsSpinner);

        radioButton1 = rootView.findViewById(R.id.radioButton1);
        radioButton2 = rootView.findViewById(R.id.radioButton2);
        radioButton3 = rootView.findViewById(R.id.radioButton3);
        radioButton4 = rootView.findViewById(R.id.radioButton4);

        submitButton = rootView.findViewById(R.id.submitButton);

        // Set up Spinner adapters
        ArrayAdapter<CharSequence> tenantTypeAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.tenant_types, android.R.layout.simple_spinner_item);
        tenantTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tenantTypeSpinner.setAdapter(tenantTypeAdapter);

        ArrayAdapter<CharSequence> bedsAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.bed_options, android.R.layout.simple_spinner_item);
        bedsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noOfBedsSpinner.setAdapter(bedsAdapter);

        // Set up Submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the form submission here
                // Your code here...
            }
        });

        return rootView;
    }
}
