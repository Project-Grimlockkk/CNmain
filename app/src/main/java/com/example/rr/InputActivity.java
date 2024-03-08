package com.example.rr;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;

public class InputActivity extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, phoneNoEditText, addressEditText, rentInrEditText, depositInrEditText, descriptionEditText;
    private Spinner tenantTypeSpinner, noOfBedsSpinner;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private Button submitButton;

    private final int GALLARY_REQ_CODE= 1000;
    ImageView imgGallary;

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
        imgGallary= findViewById(R.id.PGphotos);
        Button AddPG= findViewById(R.id.AddImagebutton);
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

        AddPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iGallary = new Intent(Intent.ACTION_PICK);
                iGallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallary,GALLARY_REQ_CODE);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the form submission here
                // Your code here...
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == GALLARY_REQ_CODE){
                imgGallary.setImageURI(data.getData());
            }
        }
    }
}
