package com.example.rr;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.io.IOException;
import java.util.List;

public class pg_info2 extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    // Views and variables
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;

    ImageView apImage, apElectricity, apWaterSupply, apCleaning ;
    TextView apName, apRent, apVacancy, apDistance, apGender,address,apPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_info2);

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


        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras(); // Retrieve data passed from the previous activity
            if (bundle != null) {
                Log.d("BundleData", "gender: " + bundle.getString("gender"));
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
//                address.setText("xyz");
                apPhoneNo.setText(bundle.getString("phoneNo"));
                if (bundle.containsKey("electricity") && bundle.getString("electricity").equals("yes")) {
                    apElectricity.setImageResource(R.drawable.checkmark_circle_svgrepo_com);
                } else {
                    apElectricity.setImageResource(R.drawable.cross_circle_svgrepo_com);
                }
                if (bundle.containsKey("waterSupply") && bundle.getString("waterSupply").equals("yes")) {
                    apWaterSupply.setImageResource(R.drawable.checkmark_circle_svgrepo_com);
                } else {
                    apWaterSupply.setImageResource(R.drawable.cross_circle_svgrepo_com);
                }
                if (bundle.containsKey("cleaningFacility") && bundle.getString("cleaningFacility").equals("yes")) {
                    apCleaning.setImageResource(R.drawable.checkmark_circle_svgrepo_com);
                } else {
                    apCleaning.setImageResource(R.drawable.cross_circle_svgrepo_com);
                }
                // Load image using Glide
                Glide.with(this).load(bundle.getString("pgPhotos")).into(apImage);
            }
        }

        ImageView whatsappIcon = findViewById(R.id.WhatsappIcon);
        whatsappIcon.setOnClickListener(v -> {
            String phoneNumber = "91680 09484";
            // Open WhatsApp with the provided phone number
            Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
            whatsappIntent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
            startActivity(whatsappIntent);
        });

        ImageView callingIcon = findViewById(R.id.calling);
        callingIcon.setOnClickListener(v -> {
            String phoneNumber = "919168009484";
            // Open phone dialer with the provided phone number
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.maps, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        checkLocationPermission();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
            // Fetch address from database (replace with your database logic)
                String pgaddress = address.getText().toString();
            // Geocode the address to get LatLng coordites
            new GeocodingTask().execute(pgaddress);
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
        }
    }
}
