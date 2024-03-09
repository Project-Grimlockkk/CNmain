package com.example.rr;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.learn.R;

public class pg_info extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pg_info, container, false);

        // Initialize Backbtn
//        ImageView Backbtn = view.findViewById(R.id.backTerms);
        ImageView whatsappIcon = view.findViewById(R.id.WhatsappIcon); // Assuming whatsappIcon is the ID of your button

//        Backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle click event for the back button
//                // For example, navigate back to the previous fragment or activity
//                requireActivity().onBackPressed(); // This will navigate back to the previous fragment or activity
//            }
//        });

        whatsappIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsAppChat(); // Call the method to open WhatsApp chat
            }
        });

        return view;
    }

    private boolean isWhatsappInstalled(){

        PackageManager packageManager = getActivity().getPackageManager();
        boolean
    }

    public void openWhatsAppChat() {
        String phoneNumber = "9657557390"; // Replace this with the desired phone number

        // Create an Intent with the ACTION_VIEW action
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // Set the WhatsApp URL with the phone number
        intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + "+91" +phoneNumber));

        // Verify that WhatsApp is installed on the device before starting the activity
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // WhatsApp is not installed, display a message or redirect to Play Store
            Toast.makeText(requireContext(), "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
            // Alternatively, you can redirect the user to the WhatsApp page on Play Store:
            // Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
            // startActivity(playStoreIntent);
        }
    }
}
