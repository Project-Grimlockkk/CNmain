package com.example.rr;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.learn.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class pg_info2 extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private ProgressDialog loadingDialog;

    // Views and variables
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private RatingBar ratingBar;
    private float userRate = 0;


    ImageView apImage, apElectricity, apWaterSupply, apCleaning;
    TextView apName, apRent, apVacancy, apDistance, apGender, address, apPhoneNo, apDeposit, pgOwner;
    TextView reviewButton;
    LinearLayout reviewsLayout;
    TextView apName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_info2);

        ratingBar = findViewById(R.id.ratingBar);
        apImage = findViewById(R.id.apImgg);
        apName = findViewById(R.id.apName);
        apRent = findViewById(R.id.apPrice);
        apVacancy = findViewById(R.id.apVacancy);
        apDistance = findViewById(R.id.apDistance);
        apGender = findViewById(R.id.apGender);
        address = findViewById(R.id.address);
        apPhoneNo = findViewById(R.id.phoneNO);
        apElectricity = findViewById(R.id.electricity);
        apWaterSupply = findViewById(R.id.waterSupply);
        apCleaning = findViewById(R.id.cleaningFacility);
        apDeposit = findViewById(R.id.apDeposit);
        pgOwner = findViewById(R.id.pgOwner);

        // Show loading dialog
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("Loading Info...");
        loadingDialog.setCancelable(false);
        loadingDialog.show();

        // Set initial color of the RatingBar
        changeRatingBarColor(ratingBar, ratingBar.getRating());

        // Add a listener to the rating bar to change color when rating changes
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Change the color of the RatingBar based on the rating
                changeRatingBarColor(ratingBar, rating);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras(); // Retrieve data passed from the previous activity
            if (bundle != null) {
                // Log bundle data
                for (String key : bundle.keySet()) {
                    Object value = bundle.get(key);
                    if (value != null) {
                        Log.d("BundleData", key + ": " + value.toString());
                    } else {
                        Log.d("BundleData", key + ": null");
                    }
                }
                // Set data to corresponding views
                apName.setText(bundle.getString("apName"));
                apRent.setText(bundle.getString("rentInr"));
                apVacancy.setText(bundle.getString("Vacancy"));
                apDistance.setText(bundle.getString("distance"));
                apGender.setText(bundle.getString("gender"));
                address.setText(bundle.getString("address"));
                apPhoneNo.setText(bundle.getString("phoneNo"));
                apDeposit.setText(bundle.getString("deposit"));
                pgOwner.setText(bundle.getString("pgOwner"));
                if (bundle.containsKey("electricity") && (bundle.getString("electricity").equals("yes") || bundle.getString("electricity").equals("Yes"))) {
                    apElectricity.setImageResource(R.drawable.checkmark_circle_svgrepo_com);
                } else {
                    apElectricity.setImageResource(R.drawable.cross_circle_svgrepo_com);
                }
                if (bundle.containsKey("waterSupply") && (bundle.getString("waterSupply").equals("yes") || bundle.getString("waterSupply").equals("Yes"))) {
                    apWaterSupply.setImageResource(R.drawable.checkmark_circle_svgrepo_com);
                } else {
                    apWaterSupply.setImageResource(R.drawable.cross_circle_svgrepo_com);
                }
                if (bundle.containsKey("cleaningFacility") && (bundle.getString("cleaningFacility").equals("yes") || bundle.getString("cleaningFacility").equals("Yes"))) {
                    apCleaning.setImageResource(R.drawable.checkmark_circle_svgrepo_com);
                } else {
                    apCleaning.setImageResource(R.drawable.cross_circle_svgrepo_com);
                }
                // Load image using Glide
                Glide.with(this).load(bundle.getString("pgPhotos")).into(apImage);
            }
        }

        apName1 = findViewById(R.id.apName);
        reviewButton = findViewById(R.id.reviewText);
        reviewsLayout = findViewById(R.id.reviews);

        // Set onClickListener for the review button
        reviewsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fetchReviewsFromFirebase(apName.getText().toString());
                DatabaseReference reviewsRef = FirebaseDatabase.getInstance().getReference("PGs")
                        .child(apName.getText().toString())
                        .child("reviews");

                reviewsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reviewsLayout.removeAllViews(); // Clear existing reviews

                        if (dataSnapshot.exists()) {
                            int reviewCount = 1;
//                            TextView reviewTextView = new TextView(getApplicationContext());
                            StringBuilder reviewsText = new StringBuilder();
//                            String all;
                            for (DataSnapshot reviewSnapshot : dataSnapshot.getChildren()) {
                                String review = reviewSnapshot.getValue(String.class);

                                reviewsText.append(reviewCount).append(". ").append(review).append("\n");
//                                addReviewToLayout(review);
//                                TextView reviewTextView = new TextView(getApplicationContext());
//                                reviewTextView.setText(reviewCount + ". " + review);
//                                reviewTextView.setTextSize(20); // Set text size to 16sp (adjust as needed)
//                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                                        LinearLayout.LayoutParams.MATCH_PARENT,
//                                        LinearLayout.LayoutParams.WRAP_CONTENT
//                                );
//                                layoutParams.setMargins(10, 5, 0, 5); // Set margins (adjust as needed)
//                                reviewTextView.setLayoutParams(layoutParams);
//                                reviewsLayout.addView(reviewTextView);
                                reviewCount++;

                            }
                            TextView reviewsTextView = new TextView(pg_info2.this);
                            reviewsTextView.setText(reviewsText.toString());
                            reviewsTextView.setTextSize(20); // Change the text size as needed

                            // Set margins
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );
                            layoutParams.setMargins(10, 5, 0, 5); // Set margins (adjust as needed)
                            reviewsTextView.setLayoutParams(layoutParams);
                            // Add TextView to the LinearLayout
                            reviewsLayout.addView(reviewsTextView);
//                            reviewButton.setText(reviewsText.toString());
//                            reviewsLayout.addView(reviewTextView);
                        } else {
                            Toast.makeText(pg_info2.this, "No reviews available for this apartment", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(pg_info2.this, "Failed to fetch reviews: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button addReviewButton = findViewById(R.id.addReview);
        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add EditText dynamically
//                addReviewEditText();
                addReviewButton.setVisibility(View.GONE);
                LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(20, 10, 20, 10); // Set padding

                // Create EditText programmatically
                EditText reviewEditText = new EditText(getApplicationContext());
                reviewEditText.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                reviewEditText.setHint("Enter your review"); // Set hint text
                reviewEditText.setSingleLine(false); // Allow multiple lines
                reviewEditText.setLines(4); // Set number of lines
                linearLayout.addView(reviewEditText); // Add EditText to the layout

                // Create submit button programmatically
                Button submitButton = new Button(getApplicationContext());
                submitButton.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                submitButton.setText("Submit");
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get review text from EditText
                        String reviewText = reviewEditText.getText().toString().trim();
                        if (!reviewText.isEmpty()) {
                            // Save the review to Firebase (replace this with your Firebase logic)
//                            saveReviewToFirebase(reviewText);
                            String apartmentName = apName.getText().toString();

                            // Get a reference to your Firebase database
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                            // Create a reference to the "Reviews" node under the specific apartment
                            DatabaseReference reviewsRef = databaseReference.child("PGs").child(apartmentName).child("reviews");

                            // Generate a unique key for the review
                            String reviewId = reviewsRef.push().getKey();
                            // You can also include other details like user ID, timestamp, etc., if needed

                            // Save the review data to the database under the generated key
                            if (reviewId != null) {
                                reviewsRef.child(reviewId).setValue(reviewText)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(pg_info2.this, "Review added successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(pg_info2.this, "Failed to add review: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                            // Optionally, clear the EditText after submitting the review
                            reviewEditText.setText("");
                        } else {
                            Toast.makeText(pg_info2.this, "Please enter a review", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                linearLayout.addView(submitButton); // Add submit button to the layout

                // Add the LinearLayout to your main layout
                LinearLayout mainLayout = findViewById(R.id.mainLayout); // Replace "mainLayout" with your actual layout ID
                mainLayout.addView(linearLayout);
            }
        });


        // Set electricity, water supply, and cleaning facility icons based on bundle data

        // Initialize WhatsApp and calling icons
        ImageView whatsappIcon = findViewById(R.id.WhatsappIcon);
        ImageView callingIcon = findViewById(R.id.calling);

        // WhatsApp icon onClickListener
        whatsappIcon.setOnClickListener(v -> {
            String phoneNumber = apPhoneNo.getText().toString();
            // Open WhatsApp with the provided phone number
            Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
            whatsappIntent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
            startActivity(whatsappIntent);
        });

        // Calling icon onClickListener
        callingIcon.setOnClickListener(v -> {
            String phoneNumber = apPhoneNo.getText().toString();
            // Open phone dialer with the provided phone number
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });

        // Initialize SupportMapFragment
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.maps, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        // Check location permission
        checkLocationPermission();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
            // Fetch address from database (replace with your database logic)
            String pgAddress = address.getText().toString();
            // Geocode the address to get LatLng coordinates
            new GeocodingTask().execute(pgAddress);
        }
    }

    // Function to check for location permission
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with actions requiring location
            } else {
                // Permission denied, show explanation dialog or handle the case
                showPermissionExplanationDialog();
            }
        }
    }

    // Function to show permission explanation dialog
    private void showPermissionExplanationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Required");
        builder.setMessage("This app requires location permission to function properly. Please grant the permission.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Request location permission again
                checkLocationPermission();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle cancellation
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // AsyncTask to perform geocoding task
    private class GeocodingTask extends AsyncTask<String, Void, LatLng> {

        @Override
        protected LatLng doInBackground(String... strings) {
            String address = strings[0];
            Geocoder geocoder = new Geocoder(pg_info2.this);
            try {
                List<Address> addresses = geocoder.getFromLocationName(address, 1);
                if (addresses != null && !addresses.isEmpty()) {
                    Address firstAddress = addresses.get(0);
                    return new LatLng(firstAddress.getLatitude(), firstAddress.getLongitude());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(LatLng latLng) {
            super.onPostExecute(latLng);
            if (latLng != null) {
                // Add a marker for the location
                mMap.addMarker(new MarkerOptions().position(latLng).title("PG"));
                // Move the camera to show the marker
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }

            // Dismiss the loading dialog after a delay
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (loadingDialog != null && loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                }
            }, 2000); // 2000 milliseconds (2 seconds)
        }

    }

    // Method to change the color of the RatingBar based on the rating
    private void changeRatingBarColor(RatingBar ratingBar, float rating) {
        int goldenYellow = Color.parseColor("#FFD700");
        ratingBar.setProgressTintList(ColorStateList.valueOf(goldenYellow));
    }


}
