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

        ImageView whatsappIcon = view.findViewById(R.id.WhatsappIcon);

        whatsappIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "9191680 09484";

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
