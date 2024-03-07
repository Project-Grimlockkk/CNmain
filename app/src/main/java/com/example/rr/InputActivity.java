package com.example.rr;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;

public class InputActivity extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, phoneNoEditText, addressEditText, rentInrEditText, depositInrEditText, descriptionEditText;
    private Spinner tenantTypeSpinner, noOfBedsSpinner;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_page1);

        // Initialize views
        firstNameEditText = findViewById(R.id.FirstNameInput);
        lastNameEditText = findViewById(R.id.LastNameInput);
        phoneNoEditText = findViewById(R.id.PhoneNoText);
        addressEditText = findViewById(R.id.AddressInput);
        rentInrEditText = findViewById(R.id.RentInput);
        depositInrEditText = findViewById(R.id.RentInput);
        descriptionEditText = findViewById(R.id.DescriptionInput);

        tenantTypeSpinner = findViewById(R.id.tenantTypeSpinner);
        noOfBedsSpinner = findViewById(R.id.noOfBedsSpinner);

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        submitButton = findViewById(R.id.submitButton);

        // Set up Spinner adapters
        ArrayAdapter<CharSequence> tenantTypeAdapter = ArrayAdapter.createFromResource(this, R.array.tenant_types, android.R.layout.simple_spinner_item);
        tenantTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tenantTypeSpinner.setAdapter(tenantTypeAdapter);

        ArrayAdapter<CharSequence> bedsAdapter = ArrayAdapter.createFromResource(this, R.array.bed_options, android.R.layout.simple_spinner_item);
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
    }
}
