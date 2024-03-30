package com.example.rr;

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
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.learn.R;

public class pg_info extends Fragment {

    ImageView apImage;
    TextView apName, apRent, apVacancy, apDistance,apGender;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pg_info, container, false);

        ImageView whatsappIcon = view.findViewById(R.id.WhatsappIcon);
        ImageView callingIcon = view.findViewById(R.id.calling);

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
                String phoneNumber = "91680 09484";

//                if(isWhatsappInstalled()){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone="+phoneNumber));
                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(requireContext(), "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        callingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "919168009484";

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });


        return view;
    }

    private boolean isWhatsappInstalled() {
        PackageManager packageManager = null;
        if (getActivity() != null) {
            packageManager = getActivity().getPackageManager();
        }
        boolean whatsappInstalled = false;

        if (packageManager != null) {
            try {
                packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                whatsappInstalled = true;
            } catch (PackageManager.NameNotFoundException e) {
                whatsappInstalled = false;
            }
        }

        return whatsappInstalled;
    }

}
