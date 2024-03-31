package com.example.rr;

import static com.google.common.io.Files.getFileExtension;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class InputActivity extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText, phoneNoEditText, addressEditText, rentInrEditText, depositInrEditText, descriptionEditText, distanceInput, apartmentNameInput, genderInput,vacancyInput, waterSupply, electricity, cleaningFacility;
    Spinner tenantTypeSpinner, noOfBedsSpinner;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    Button submitButton, addPgPhotos;
    String imageUrl;

    Uri imgUri;

    private final int GALLARY_REQ_CODE= 1000;
    ImageView imgGallary;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    List<Uri> selectedImageUris = new ArrayList<>();
//    ActivityResultLauncher<Intent> activityResultLauncher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_page1);
        databaseReference = FirebaseDatabase.getInstance().getReference("properties");
        storageReference = FirebaseStorage.getInstance().getReference();


        // Initialize views
        firstNameEditText = findViewById(R.id.FirstNameInput);
        lastNameEditText = findViewById(R.id.LastNameInput);
        phoneNoEditText = findViewById(R.id.PhoneNoText);
        addressEditText = findViewById(R.id.AddressInput);
        rentInrEditText = findViewById(R.id.RentInput);
        depositInrEditText = findViewById(R.id.DepositInput);
        descriptionEditText = findViewById(R.id.DescriptionInput);
        imgGallary= findViewById(R.id.PGphotos);

        Button AddPG= findViewById(R.id.AddImagebutton);
        tenantTypeSpinner = findViewById(R.id.tenantTypeSpinner);
        noOfBedsSpinner = findViewById(R.id.noOfBedsSpinner);
        distanceInput = findViewById(R.id.distanceInput);
        apartmentNameInput=findViewById(R.id.apartmentNameInput);
        genderInput=findViewById(R.id.genderInput);
        vacancyInput= findViewById(R.id.VacancyInput);
        waterSupply= findViewById(R.id.waterSupply);
        electricity= findViewById(R.id.electricity);
        cleaningFacility= findViewById(R.id.cleaningFacility);






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

                StorageReference storageReference1=FirebaseStorage.getInstance().getReference().child("PG Images")
                        .child(imgUri.getLastPathSegment());

                storageReference1.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri urlImage= uriTask.getResult();
                        imageUrl=urlImage.toString();

                        // upload data
//                        Integer imgurii = Integer.parseInt(imgUri.toString());
                        String firstName=firstNameEditText.getText().toString();
                        String lastName=lastNameEditText.getText().toString();
                        String phoneNo=phoneNoEditText.getText().toString();
                        String vaccancy = vacancyInput.getText().toString();
                        String address=addressEditText.getText().toString();
//                        Integer rent=Integer.parseInt(rentInrEditText.getText().toString());
                        String rent = rentInrEditText.getText().toString();
//                        Integer deposit=Integer.parseInt(depositInrEditText.getText().toString());
                        String distance=distanceInput.getText().toString();
                        String apName=apartmentNameInput.getText().toString();
                        String apGender=genderInput.getText().toString();
                        String apElectricity=genderInput.getText().toString();
                        String apWaterSupply=genderInput.getText().toString();
                        String apCleaningFacility=genderInput.getText().toString();

                        RoomDetailsClass roomDetailsClass= new RoomDetailsClass(apName, rent, vaccancy, distance, imageUrl, apGender,address,phoneNo,apElectricity,apWaterSupply,apCleaningFacility);

//                        Property property1= new Property(imageUrl,vaccancy,rent,distance,apName,gender);
//                        RoomDetailsModel roomDetailsModel= new RoomDetailsModel(imgurii,apName, distance,vacancy,rent,gender);

                        FirebaseDatabase.getInstance().getReference("PGs").child(apName)
                                .setValue(roomDetailsClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(InputActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(InputActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

                // Handle the form submission here
                // Your code here...
//
            }
        });
//        private String getFileExtension(Uri fileUri){
//            ContentResolver contentResolver = getContentResolver();
//            MimeTypeMap mime = MimeTypeMap.getSingleton();
//            return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
//        }

        AddPG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent iGallary = new Intent(Intent.ACTION_PICK);
                iGallary.setType("image/*");
                iGallary.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
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
//                            if (data.getClipData() != null){
//                                int count = data.getClipData().getItemCount();
//                                for (int i = 0; i < count; i++) {
////                                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
//                                     imgUri = data.getData();
//                                    selectedImageUris.add(imgUri);
//                                    imgGallary.setImageURI(imgUri);
//                                    // Display selected image or add it to a list for display
//                                }
//                            } else if (data.getData() != null) {
//                                Uri imageUri = data.getData();
//                                selectedImageUris.add(imageUri);
//                                // Display selected image or add it to a list for display
//                            }
                            imgUri = data.getData();
                            imgGallary.setImageURI(imgUri);
                        }
                        else{
                            Toast.makeText(InputActivity.this, "No Image Selected", Toast.LENGTH_SHORT);
                        }
                    }
                }
        );

//

        imgGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
//                photoPicker.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                activityResultLauncher.launch(photoPicker);

//                Intent photoPicker = new Intent();
//                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);

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

//    private void uploadImageToFirebase(Uri imgUri) {
//        String caption = descriptionEditText.getText().toString();
//        final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imgUri));
//        imageReference.putFile(imgUri).addOnSuccessListener(taskSnapshot -> {
//            imageReference.getDownloadUrl().addOnSuccessListener(uri -> {
//                String imageUrl = uri.toString();
//                saveImageUrlToDatabase(imageUrl, caption);
//            });
//        }).addOnFailureListener(e -> {
//            Toast.makeText(InputActivity.this, "Failed" + e.getMessage(), Toast.LENGTH_LONG).show();
//        });
//    }

//    private void saveImageUrlToDatabase(String imageUrl, String caption) {
//        DataClass dataClass = new DataClass(imageUrl, caption);
//        String key = databaseReference.push().getKey();
//        if (key != null) {
//            databaseReference.child(key).setValue(dataClass).addOnSuccessListener(aVoid -> {
//                Toast.makeText(InputActivity.this, "Image uploaded and data saved", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(e -> {
//                Toast.makeText(InputActivity.this, "Failed to save image data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//        }
//    }
//    private String getFileExtension(Uri fileUri){
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
//    }
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

//    Property property = new Property();
//                property.setPgPhotos(imgUri.toString());
//                property.setFirstName(firstNameEditText.getText().toString());
//                property.setLastName(lastNameEditText.getText().toString());
//                property.setPhoneNo(phoneNoEditText.getText().toString());
//                property.setAddress(addressEditText.getText().toString());
//                property.setRentInr(Integer.parseInt(rentInrEditText.getText().toString()));
//                property.setDepositInr(Integer.parseInt(depositInrEditText.getText().toString()));
//                property.setDistance(distanceInput.getText().toString());
//                property.setApName(apartmentNameInput.getText().toString());
//                property.setGender(genderInput.getText().toString());


//                property.setTenantType(tenantTypeSpinner.getSelectedItem().toString());
//                property.setNumberOfBeds(Integer.parseInt(noOfBedsSpinner.getSelectedItem().toString()));
//                property.setElectricityIncluded(radioButton1.isChecked());
//                property.setCleaningFacility(radioButton2.isChecked());
//                property.setInternetConnectivity(radioButton3.isChecked());
//                property.setWaterSupply(radioButton4.isChecked());
// Set other properties as needed

// Get Firestore instance
//                FirebaseFirestore db = FirebaseFirestore.getInstance();

//                String propertyId = databaseReference.push().getKey();
//
//                // Add a new document with a generated ID
//                databaseReference.child(propertyId).setValue(property)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                // Data added successfully
//                                Toast.makeText(InputActivity.this, "Property added successfully!", Toast.LENGTH_SHORT).show();
//                                clearInputFields();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Failed to add data
//                                Toast.makeText(InputActivity.this, "Failed to add property. Please try again.", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                if (imgUri != null){
////                    uploadToFirebase(imgUri);
//                    String caption = descriptionEditText.getText().toString();
//                    final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imgUri));
//                    imageReference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    DataClass dataClass = new DataClass(uri.toString(), caption);
//                                    String key = databaseReference.push().getKey();
//                                    if (key != null) {
//                                        databaseReference.child(key).setValue(dataClass).addOnSuccessListener(aVoid -> {
//                                            Toast.makeText(InputActivity.this, "Image uploaded and data saved", Toast.LENGTH_SHORT).show();
//                                        }).addOnFailureListener(e -> {
//                                            Toast.makeText(InputActivity.this, "Failed to save image data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        });
//                                    }
////                                    DataClass dataClass = new DataClass(imgUri.toString(),caption);
////                                    String key = databaseReference.push().getKey();
////                                    databaseReference.child(key).setValue(dataClass);
////                                    progressBar.setVisibility(View.INVISIBLE);
//                                    Toast.makeText(InputActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
////                                    Intent intent = new Intent(UploadActivity.this, MainActivity.class);
////                                    startActivity(intent);
////                                    finish();
//                                }
//                            });
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
////                            progressBar.setVisibility(View.INVISIBLE);
//                            Toast.makeText(InputActivity.this, "Failed" + e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//                } else  {
//                    Toast.makeText(InputActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
//                }
