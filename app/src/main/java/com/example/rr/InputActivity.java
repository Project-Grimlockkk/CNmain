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

//<<<<<<< HEAD
    EditText firstNameEditText, lastNameEditText, phoneNoEditText, addressEditText, rentInrEditText, depositInrEditText, descriptionEditText, distanceInput, apartmentNameInput, genderInput,vacancyInput, waterSupply, electricity, cleaningFacility;
//=======
//    EditText firstNameEditText, lastNameEditText, phoneNoEditText, addressEditText, rentInrEditText, depositInrEditText, descriptionEditText, distanceInput, apartmentNameInput, genderInput, vacancyInput;
//>>>>>>> 88eb193fb7e724b8e2dbf88f6cb561acad833424
    Spinner tenantTypeSpinner, noOfBedsSpinner;

    Button submitButton, addPgPhotos;
    String imageUrl;

    Uri imgUri;

    private final int GALLARY_REQ_CODE = 1000;
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
        imgGallary = findViewById(R.id.PGphotos);

        Button AddPG = findViewById(R.id.AddImagebutton);
        distanceInput = findViewById(R.id.distanceInput);
//<<<<<<< HEAD
        apartmentNameInput=findViewById(R.id.apartmentNameInput);
        genderInput=findViewById(R.id.genderInput);
        vacancyInput= findViewById(R.id.VacancyInput);
        waterSupply= findViewById(R.id.waterSupply);
        electricity= findViewById(R.id.electricity);
        cleaningFacility= findViewById(R.id.cleaningFacility);



////=======
//        apartmentNameInput = findViewById(R.id.apartmentNameInput);
//        genderInput = findViewById(R.id.genderInput);
//        vacancyInput = findViewById(R.id.VacancyInput);
//>>>>>>> 88eb193fb7e724b8e2dbf88f6cb561acad833424


        submitButton = findViewById(R.id.submitButton);



        // Set up Submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StorageReference storageReference1 = FirebaseStorage.getInstance().getReference().child("PG Images")
                        .child(imgUri.getLastPathSegment());

                storageReference1.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete()) ;
                        Uri urlImage = uriTask.getResult();
                        imageUrl = urlImage.toString();

                        // upload data
//                        Integer imgurii = Integer.parseInt(imgUri.toString());
                        String firstName = firstNameEditText.getText().toString();
                        String lastName = lastNameEditText.getText().toString();
                        String phoneNo = phoneNoEditText.getText().toString();
                        String vaccancy = vacancyInput.getText().toString();
                        String address = addressEditText.getText().toString();
//                        Integer rent=Integer.parseInt(rentInrEditText.getText().toString());
                        String rent = rentInrEditText.getText().toString();
//                        Integer deposit=Integer.parseInt(depositInrEditText.getText().toString());
//<<<<<<< HEAD
                        String distance=distanceInput.getText().toString();
                        String apName=apartmentNameInput.getText().toString();
                        String apGender=genderInput.getText().toString();
                        String apElectricity=electricity.getText().toString();
                        String apWaterSupply=waterSupply.getText().toString();
                        String apCleaningFacility=cleaningFacility.getText().toString();

                        RoomDetailsClass roomDetailsClass= new RoomDetailsClass(apName, rent, vaccancy, distance, imageUrl, apGender,address,phoneNo,apElectricity,apWaterSupply,apCleaningFacility);
////=======
//                        String distance = distanceInput.getText().toString();
//                        String apName = apartmentNameInput.getText().toString();
//                        String apGender = genderInput.getText().toString();
//
//                        RoomDetailsClass roomDetailsClass = new RoomDetailsClass(apName, rent, vaccancy, distance, imageUrl, apGender, address, phoneNo);
//>>>>>>> 88eb193fb7e724b8e2dbf88f6cb561acad833424

//                        Property property1= new Property(imageUrl,vaccancy,rent,distance,apName,gender);
//                        RoomDetailsModel roomDetailsModel= new RoomDetailsModel(imgurii,apName, distance,vacancy,rent,gender);

                        FirebaseDatabase.getInstance().getReference("PGs").child(apName)
                                .setValue(roomDetailsClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
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
            }
        });

//        AddPG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent iGallary = new Intent(Intent.ACTION_PICK);
//                iGallary.setType("image/*");
//                iGallary.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                iGallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                startActivityForResult(iGallary, GALLARY_REQ_CODE);
//            }
//        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            imgUri = data.getData();
                            imgGallary.setImageURI(imgUri);
                        } else {
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
                activityResultLauncher.launch(photoPicker);
            }
        });
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
}