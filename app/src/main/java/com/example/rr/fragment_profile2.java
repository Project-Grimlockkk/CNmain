package com.example.rr;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.learn.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_profile2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_profile2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_profile2() {
        // Required empty public constructor
    }
    public static fragment_profile2 newInstance(String param1, String param2) {
        fragment_profile2 fragment = new fragment_profile2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile2, container, false);

        RelativeLayout aboutView = view.findViewById(R.id.aboutapp);
        RelativeLayout termsView = view.findViewById(R.id.termss);
        RelativeLayout privacyView = view.findViewById(R.id.privacy);
        RelativeLayout shareView = view.findViewById(R.id.share);
        RelativeLayout changePass = view.findViewById(R.id.changePass);


        aboutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new aboutPage();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.profilePage, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        termsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.setScaleX(0.9f);
                view.setScaleY(0.9f);
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setScaleX(1.0f);
                        view.setScaleY(1.0f);
                    }
                }, 100);

                Fragment fragment= new termsPage();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.profilePage, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        privacyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment= new privacyPage();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.profilePage, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_forgetPass forgetPassFragment = new fragment_forgetPass();

                // Get the FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Begin a FragmentTransaction
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Replace the current fragment with fragment_forgetPass
                transaction.replace(R.id.profilePage, forgetPassFragment);

                // Add the transaction to the back stack
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain"); // Set MIME type to text/plain for sharing text
                String body = "Download This App";
                String sub = "https://www.youtube.com/watch?v=i41rmT-GDXc"; // This is the subject
                intent.putExtra(Intent.EXTRA_TEXT, body); // Set the body of the message
                intent.putExtra(Intent.EXTRA_SUBJECT, sub); // Set the subject of the message
                startActivity(Intent.createChooser(intent, "Share using: "));
            }
        });
        return view;
    }
}