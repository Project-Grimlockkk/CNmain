package com.example.rr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.learn.R;

public class pg_info2 extends AppCompatActivity {

    ImageView apImage;
    TextView apName, apRent, apVacancy, apDistance, apGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_info2);

        apImage = findViewById(R.id.apImgg);
        apName = findViewById(R.id.apName);
        apRent = findViewById(R.id.apPrice);
        apVacancy = findViewById(R.id.apVacancy);
        apDistance = findViewById(R.id.apDistance);
        apGender = findViewById(R.id.gender);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras(); // Retrieve data passed from the previous activity
            if (bundle != null) {
                // Set data to corresponding views
                apName.setText(bundle.getString("apName"));
                apRent.setText(bundle.getString("rentInr"));
                apVacancy.setText(bundle.getString("Vacancy"));
                apDistance.setText(bundle.getString("distance"));
                apGender.setText(bundle.getString("gender"));
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
    }
    private boolean isWhatsappInstalled() {
        PackageManager packageManager = getPackageManager();
        try {
            // Check if WhatsApp is installed on the device
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}