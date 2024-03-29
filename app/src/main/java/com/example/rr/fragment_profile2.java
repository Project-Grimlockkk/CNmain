package com.example.rr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.learn.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
        RelativeLayout feedback = view.findViewById(R.id.feedback);
        RelativeLayout logout = view.findViewById(R.id.logout);
        RelativeLayout rateus = view.findViewById(R.id.rateus);
        RelativeLayout pGinfo = view.findViewById(R.id.PGinfo);
        AppCompatButton editProfileButton = view.findViewById(R.id.editProfileButton);

        dp = FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();

        nightModeSwitch = view.findViewById(R.id.nightModeSwitch1);

        // Load night mode state from SharedPreferences
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        nightModeEnabled = sharedPreferences.getBoolean("nightModeEnabled", true);
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
            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EditProfileActivity when the button is clicked
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        aboutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new aboutPage();
                loadFragment(fragment, true);
            }
        });

        termsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new termsPage();
                loadFragment(fragment, true);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new FeedbackFragment();
                loadFragment(fragment, true);
            }
        });

        pGinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new pg_info();
                loadFragment(fragment, true);
            }
        });

        privacyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new privacyPage();
                loadFragment(fragment, true);
            }
        });

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= new RateUsFragment();
                loadFragment(fragment, true);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Log Out");
                builder.setMessage("Are you sure you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        Intent intent = new Intent(requireContext(), LoginScreen.class);
                        startActivity(intent);
                        requireActivity().finish();
                        Toast.makeText(requireContext(), "Successfully logged out", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, simply dismiss the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
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
