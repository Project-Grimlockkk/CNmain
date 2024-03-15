package com.example.rr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learn.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class InputActivity extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText, phoneNoEditText, addressEditText, rentInrEditText, depositInrEditText, descriptionEditText;
    Spinner tenantTypeSpinner, noOfBedsSpinner;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    Button submitButton, addPgPhotos;

    Uri uri;

    private final int GALLARY_REQ_CODE= 1000;
    ImageView imgGallary;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_page1);
        databaseReference = FirebaseDatabase.getInstance().getReference("properties");


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
                Property property = new Property();
                property.setFirstName(firstNameEditText.getText().toString());
                property.setLastName(lastNameEditText.getText().toString());
                property.setPhoneNo(phoneNoEditText.getText().toString());
                property.setAddress(addressEditText.getText().toString());
                property.setRentInr(Integer.parseInt(rentInrEditText.getText().toString()));
                property.setDepositInr(Integer.parseInt(depositInrEditText.getText().toString()));
//                property.setTenantType(tenantTypeSpinner.getSelectedItem().toString());
//                property.setNumberOfBeds(Integer.parseInt(noOfBedsSpinner.getSelectedItem().toString()));
//                property.setElectricityIncluded(radioButton1.isChecked());
//                property.setCleaningFacility(radioButton2.isChecked());
//                property.setInternetConnectivity(radioButton3.isChecked());
//                property.setWaterSupply(radioButton4.isChecked());
                // Set other properties as needed

                // Get Firestore instance
//                FirebaseFirestore db = FirebaseFirestore.getInstance();

                String propertyId = databaseReference.push().getKey();

                // Add a new document with a generated ID
                databaseReference.child(propertyId).setValue(property)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Data added successfully
                                Toast.makeText(InputActivity.this, "Property added successfully!", Toast.LENGTH_SHORT).show();
                                clearInputFields();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Failed to add data
                                Toast.makeText(InputActivity.this, "Failed to add property. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        AddPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iGallary = new Intent(Intent.ACTION_PICK);
                iGallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallary,GALLARY_REQ_CODE);
            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            imgGallary.setImageURI(uri);
                        }
                        else{
                            Toast.makeText(InputActivity.this, "No Image Selected", Toast.LENGTH_SHORT);
                        }
                    }
                }
        );

        imgGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
//        submitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveData();
//            }
//        });
    }

    private void clearInputFields() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        phoneNoEditText.setText("");
        addressEditText.setText("");
        rentInrEditText.setText("");
        depositInrEditText.setText("");
        // Clear other input fields as needed
    }
//    public void saveData(){
//
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
//                .child(uri.getLastPathSegment());
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(InputActivity.this);
//        builder.setCancelable(false);
//        builder.setView(R.layout.pro)
//    }
}
