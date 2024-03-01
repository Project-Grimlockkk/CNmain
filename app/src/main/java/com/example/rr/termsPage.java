package com.example.rr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.learn.R;

public class termsPage extends Fragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.terms_page, container, false);

        // Initialize Backbtn
        ImageView Backbtn = view.findViewById(R.id.backTerms);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for the back button
                // For example, navigate back to the previous fragment or activity
                requireActivity().onBackPressed(); // This will navigate back to the previous fragment or activity
            }
        });

        return view;
    }
}