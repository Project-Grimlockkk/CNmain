package com.example.rr;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.learn.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<RoomDetailsModel> arrDetails = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        Button addingPGButton = rootView.findViewById(R.id.addingPG);
        ImageView filterbutton = rootView.findViewById(R.id.filterr);

        addingPGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the InputActivity
                Intent intent = new Intent(getActivity(), InputActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recycler = rootView.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrDetails.add(new RoomDetailsModel(R.drawable.pg_1, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Shivdatt Apartment", " 400m", "4", "Rs. 1800/-","Boys"));
        arrDetails.add(new RoomDetailsModel(R.drawable.pg_2, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Asher Villa", " 100m", "6", "Rs. 2500/-","Boys"));
        arrDetails.add(new RoomDetailsModel(R.drawable.pg_3, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Silver Oak Apartment", " 700m", "7", "Rs. 2000/-","Boys"));
        arrDetails.add(new RoomDetailsModel(R.drawable.pg_4, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Utkarsh Sadan", " 400m", "6", "Rs. 0/-","Girls"));
        arrDetails.add(new RoomDetailsModel(R.drawable.pg_5, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Harishankar Apartment", " 700m", "6", "Rs. 2000/-","Boys"));
        arrDetails.add(new RoomDetailsModel(R.drawable.pg_6, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Shree Complex", " 1000m", "6", "Rs. 1800/-","Boys"));

        recyclerContactAdapter adapter = new recyclerContactAdapter(getActivity(), arrDetails);
        recycler.setAdapter(adapter);

        return rootView;
    }
}