package com.example.rr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learn.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<RoomDetailsModel> arrDetails = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listingpage, container, false);

        RecyclerView recycler = rootView.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrDetails.add(new RoomDetailsModel(R.drawable.bg, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Harishankar Apartment", " 700m", "2", "RS 2000"));
        arrDetails.add(new RoomDetailsModel(R.drawable.bg, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Harishankar Apartment", " 700m", "2", "RS 2000"));
        arrDetails.add(new RoomDetailsModel(R.drawable.bg, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Harishankar Apartment", " 700m", "2", "RS 2000"));

        recyclerContactAdapter adapter = new recyclerContactAdapter(getActivity(), arrDetails);
        recycler.setAdapter(adapter);

        return rootView;
    }
}