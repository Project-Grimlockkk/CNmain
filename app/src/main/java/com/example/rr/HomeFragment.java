package com.example.rr;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.load.engine.Resource;
import com.example.learn.R;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RadioButton filterBoys, filterGirls, filterVeg, filterNonveg, filterVeryClose, filterMedDistance;
    RadioGroup filterGroup;

    RecyclerView recyclerView1;
    List<RoomDetailsClass> datalist;

    ArrayList<RoomDetailsModel> arrDetails = new ArrayList<>();

    ValueEventListener valueEventListener;
//    DatabaseReference databaseReference;

    DatabaseReference databaseReference;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

//        filterGroup = rootView.findViewById(R.id.filterGroup);
//        filterBoys = rootView.findViewById(R.id.filterBoys);
//        filterGirls = rootView.findViewById(R.id.filterGirls);
//        filterVeg = rootView.findViewById(R.id.filterVeg);
//        filterNonveg = rootView.findViewById(R.id.filterNonveg);
//        filterVeryClose = rootView.findViewById(R.id.filterveryclose);
//        filterMedDistance = rootView.findViewById(R.id.filterMeddistance);
//
//        filterGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId == R.id.filterBoys) {
//                filterData("Boys");
//            } else if (checkedId == R.id.filterGirls) {
//                filterData("Girls");
//            } else {
//                    // Handle default case
//                }
//        });

        Button addingPGButton = rootView.findViewById(R.id.addingPG);

        addingPGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the InputActivity
                Intent intent = new Intent(getActivity(), InputActivity.class);
                startActivity(intent);
            }
        });

        recyclerView1 = rootView.findViewById(R.id.recycler);
//        recyclerView1.
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),1);
//        recycler.setHasFixedSize(true);
        recyclerView1.setLayoutManager(gridLayoutManager);

        datalist = new ArrayList<>();

//        Resources resources = getResources();
//        Uri imageUri1 = Uri.parse(resources.getResourceName(R.drawable.pg_1));
//        Uri imageUri2 = Uri.parse(resources.getResourceName(R.drawable.pg_2));
//        Uri imageUri3 = Uri.parse(resources.getResourceName(R.drawable.pg_3));
//        Uri imageUri4 = Uri.parse(resources.getResourceName(R.drawable.pg_4));
//        Uri imageUri5 = Uri.parse(resources.getResourceName(R.drawable.pg_5));
//        Uri imageUri6 = Uri.parse(resources.getResourceName(R.drawable.pg_5));

//        arrDetails.add(new RoomDetailsModel(R.drawable.pg_1, "Shivdatt Apartment", " 400m", "4", "Rs. 1800/-","Boys"));
//        arrDetails.add(new RoomDetailsModel(R.drawable.pg_2,  "Asher Villa", " 100m", "6", "Rs. 2500/-","Boys"));
//        arrDetails.add(new RoomDetailsModel(R.drawable.pg_3,  "Silver Oak Apartment", " 700m", "7", "Rs. 2000/-","Boys"));
//        arrDetails.add(new RoomDetailsModel(R.drawable.pg_4,"Utkarsh Sadan", " 400m", "6", "Rs. 0/-","Girls"));
//        arrDetails.add(new RoomDetailsModel(R.drawable.pg_5,"Harishankar Apartment", " 700m", "6", "Rs. 2000/-","Boys"));
//        arrDetails.add(new RoomDetailsModel(R.drawable.pg_6, "Shree Complex", " 1000m", "6", "Rs. 1800/-","Boys"));

        recyclerContactAdapter adapter = new recyclerContactAdapter(getActivity(), datalist);
        recyclerView1.setAdapter(adapter);
//        arrDetails = new ArrayList<>();
//        adapter= new recyclerContactAdapter(getContext(), arrDetails);
//        recycler.setAdapter(adapter);

//        RoomDetailsClass roomDetailsClass;

        databaseReference= FirebaseDatabase.getInstance().getReference("PGs");

        valueEventListener= databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();
//                RoomDetailsClass roomDetailsClass = new RoomDetailsClass();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
//                    DataClass dataClass= itemSnapshot.getValue(D)
                    String apName= itemSnapshot.getKey();
                    String key= itemSnapshot.getKey();
                    String parentKey = snapshot.getKey();
                    Log.d("FirebaseKey", "Parent Key: " + parentKey);
                    Log.d("FirebaseKey", "Key: " + key);
//                    String apDistance =itemSnapshot.child("distance").getValue(String.class);
//                    String gender =itemSnapshot.child("gender").getValue(String.class);
//                    String apRent =itemSnapshot.child("rentInr").getValue(String.class);
//                    String apVacancy =itemSnapshot.child("Vaccancy").getValue(String.class);
//                    String apPhoto =itemSnapshot.child("pgPhotos").getValue(String.class);

//                    RoomDetailsClass roomDetailsClass = new RoomDetailsClass();
//                    roomDetailsClass.setApName(apName);
//                    roomDetailsClass.setDistance(apDistance);
//                    roomDetailsClass.setGender(gender);
//                    roomDetailsClass.setRentInr(apRent);
//                    roomDetailsClass.setVaccancy(apVacancy);
//                    roomDetailsClass.setPgPhotos(apPhoto);
//                    roomDetailsClass.setApName(apName);
                    for(DataSnapshot childSnapshot: itemSnapshot.getChildren()){
                        String grandChildKey = childSnapshot.getKey();
                        Log.d("FirebaseKey", "Grandchild Key: " + grandChildKey);
//                        roomDetailsClass= itemSnapshot.getValue(RoomDetailsClass.class);
//                        roomDetailsClass.setApName(apName);
//                        roomDetailsClass.setRentInr(rentInr);
//                        datalist.add(roomDetailsClass);
                    }
                    RoomDetailsClass roomDetailsClass= itemSnapshot.getValue(RoomDetailsClass.class);
                    datalist.add(roomDetailsClass);
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                arrDetails.clear();
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    RoomDetailsModel dataClass = dataSnapshot.getValue(RoomDetailsModel.class);
//                    arrDetails.add(dataClass);
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        return rootView;
    }
//    private void filterData(String genderFilter) {
////        List<RoomDetailsClass> filteredList = new ArrayList<>();
////        for (RoomDetailsClass room : datalist) {
////            if (room.getGender().equals(filter)) {
////                filteredList.add(room);
////            }
////        }
//        recyclerContactAdapter adapter = (recyclerContactAdapter) recyclerView1.getAdapter();
//        adapter.filterList(genderFilter);
//    }
}