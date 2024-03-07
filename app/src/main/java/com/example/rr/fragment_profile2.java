package com.example.rr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_profile2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_profile2 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Switch nightModeSwitch;
    boolean nightModeEnabled;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String mParam1;
    private String mParam2;
    private FirebaseFirestore dp;
    private FirebaseAuth mAuth;

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
        RelativeLayout logout = view.findViewById(R.id.logout);

        dp = FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();


        nightModeSwitch = view.findViewById(R.id.nightModeSwitch1);

        // Load night mode state from SharedPreferences
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        nightModeEnabled = sharedPreferences.getBoolean("nightModeEnabled", false);
        nightModeSwitch.setChecked(nightModeEnabled);

        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save night mode state to SharedPreferences
                editor = sharedPreferences.edit();
                editor.putBoolean("nightModeEnabled", isChecked);
                editor.apply();

                if (isChecked) {
                    // Enable night mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    // Disable night mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

                Fragment currentFragment = getParentFragmentManager().findFragmentById(R.id.profilePage);
                if (currentFragment instanceof fragment_profile2) {
                    // If the current fragment is the profile fragment, reload it
                    loadFragment(new fragment_profile2(), false);
                }
            }
        });

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
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent= new Intent(requireContext(),LoginScreen.class);
                startActivity(intent);
                requireActivity().finish();
                Toast.makeText(requireContext(), "Successfully logout", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        if (isAppInitialized) {
            transaction.replace(R.id.profilePage, fragment);
        } else {
            transaction.replace(R.id.profilePage, fragment);
        }
        transaction.commit();
    }

}
