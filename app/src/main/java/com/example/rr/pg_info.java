package com.example.rr;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.Manifest;

import com.bumptech.glide.Glide;
import com.example.learn.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// Import statements omitted for brevity

public class pg_info extends Fragment implements OnMapReadyCallback {

    // Constants for location permission
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    // Views and variables
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;

    ImageView apImage;
    TextView apName, apRent, apVacancy, apDistance,apGender;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pg_info, container, false);

        // Initialize views
        ImageView whatsappIcon = view.findViewById(R.id.WhatsappIcon);
        ImageView callingIcon = view.findViewById(R.id.calling);

//<<<<<<< HEAD
        apImage=view.findViewById(R.id.apImgg);
        apName=view.findViewById(R.id.apName);
        apRent=view.findViewById(R.id.apPrice);
        apVacancy=view.findViewById(R.id.apVacancy);
        apDistance=view.findViewById(R.id.apDistance);
        apGender=view.findViewById(R.id.gender);

        Bundle bundle = getArguments();
        if(bundle!=null){
            apName.setText(bundle.getString("apName"));
            apRent.setText(bundle.getString("rentInr"));
            apVacancy.setText(bundle.getString("Vacancy"));
            apDistance.setText(bundle.getString("distance"));
            apGender.setText(bundle.getString("gender"));
            Glide.with(this).load(bundle.getString("pgPhotos")).into(apImage);
        }

        whatsappIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "9168009484";
//                if (isWhatsappInstalled()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
                    startActivity(intent);
//                } else {
//                    Toast.makeText(requireContext(), "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        // Phone call action
        callingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "919168009484";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        // Initialize the map fragment
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.maps, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        checkLocationPermission();

        return view;
    }

    // Check if WhatsApp is installed
    private boolean isWhatsappInstalled() {
        PackageManager packageManager = requireActivity().getPackageManager();
        try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
            // Add a marker for a specific location (replace with your desired PG location)

            LatLng pgLatLng = new LatLng(16.8447, 74.5978); // Example coordinates (Patna)

            mMap.addMarker(new MarkerOptions().position(pgLatLng).title("PG"));

            // Move the camera to show the marker
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pgLatLng, 17));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
    }

    // Function to check for location permission
    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    // Function to request location permission
    private void requestLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
    }

    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with actions requiring location
            } else {
                // Permission denied, handle the case
            }
        }
    }
    private void showPermissionExplanationDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Permission Required");
        builder.setMessage("This app requires location permission to function properly. Please grant the permission.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestLocationPermission();
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
}