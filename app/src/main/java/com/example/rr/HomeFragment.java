package com.example.rr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
//<<<<<<< HEAD
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bumptech.glide.load.engine.Resource;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.learn.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
//<<<<<<< HEAD
import java.util.Arrays;
//=======
import java.util.Collections;
import java.util.Comparator;
//>>>>>>> d10b120a82efb2e733cde0bf32e034d2bd3da674
import java.util.List;

class RoomDetailsComparator implements Comparator<RoomDetailsClass> {
    @Override
    public int compare(RoomDetailsClass room1, RoomDetailsClass room2) {
        // Compare by apName

        int result = room1.getGender().compareToIgnoreCase(room2.getGender());
        if (result != 0) {
            return result;
        }
        result = room1.getDistance().compareToIgnoreCase(room2.getDistance());
        if (result != 0) {
            return result;
        }

        result = room1.getVaccancy().compareToIgnoreCase(room2.getVaccancy());
        if (result != 0) {
            return result;
        }

        result = room1.getRentInr().compareToIgnoreCase(room2.getRentInr());
        if (result != 0) {
            return result;
        }

        result = room1.getApName().compareToIgnoreCase(room2.getApName());
        if (result != 0) {
            return result;
        }

        // If prices are equal, compare by another parameter
        // Add more comparisons as needed
        // For example, compare by vacancy
        return room1.getVaccancy().compareToIgnoreCase(room2.getVaccancy());
    }
}

public class HomeFragment extends Fragment {

    RadioButton filterBoys, filterGirls, filterVeg, filterNonveg, filterVeryClose, filterMedDistance;
    RadioGroup filterGroup;

    RecyclerView recyclerView1;
    List<RoomDetailsClass> datalist;
    recyclerContactAdapter adapter;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;

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

        Spinner spinner = rootView.findViewById(R.id.spinnerwelcome);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(requireContext(),
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);

        List<List<String>> apartmentNamesList = new ArrayList<>();
        apartmentNamesList.add(Arrays.asList("Asher Villa", "Flat","Room", "Shivdatt Apartment","--", "Flat", "Ganesh Sahanivas","Silver Oak Apartment Flat No 9"));
        apartmentNamesList.add(Arrays.asList("Silver Oak Apartment", "Sahara Boys Hostel","Harishankar Apartment","Room"));
        apartmentNamesList.add(Arrays.asList("Shree Complex", "Sahara Boys Hostel", "Unknown"));
        apartmentNamesList.add(Arrays.asList("bshwjdbe", "-"));
        apartmentNamesList.add(Arrays.asList("Not known", "Ganesh Sahanivas"));


        // Handle Spinner item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle spinner item selection
                String selectedItem = parent.getItemAtPosition(position).toString();
                List<String> apartmentNames = apartmentNamesList.get(position);
                // Do something with the selected item
                filterDataa(apartmentNames);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });


        Button addingPGButton = rootView.findViewById(R.id.addingPG);
        addingPGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InputActivity.class);
                startActivity(intent);
            }
        });

        Button addingReviewButton = rootView.findViewById(R.id.addingReviews);
        addingReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), reviewActivity.class);
                startActivity(intent);
            }
        });

        recyclerView1 = rootView.findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView1.setLayoutManager(gridLayoutManager);

        datalist = new ArrayList<>();

        adapter = new recyclerContactAdapter(getActivity(), datalist); // Initialize adapter
        recyclerView1.setAdapter(adapter);   // Set adapter to RecyclerView

        databaseReference = FirebaseDatabase.getInstance().getReference("PGs");


        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    RoomDetailsClass roomDetailsClass = itemSnapshot.getValue(RoomDetailsClass.class);
                    datalist.add(roomDetailsClass);
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
            }
        });

        SearchView searchView = rootView.findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });

        return rootView;
    }


    private void filterData(String query) {
        if (adapter != null) {
            ArrayList<RoomDetailsClass> filteredList = new ArrayList<>();
            for (RoomDetailsClass roomDetails : datalist) {
                if (roomDetails.getApName().toLowerCase().contains(query.toLowerCase()) ||
                        roomDetails.getGender().toLowerCase().contains(query.toLowerCase()) ||
                        roomDetails.getVaccancy().toLowerCase().contains(query.toLowerCase()) ||
                        roomDetails.getRentInr().toLowerCase().contains(query.toLowerCase()) ||
                        roomDetails.getDistance().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(roomDetails);
                }
            }
//            Collections.sort(filteredList, new RoomDetailsComparator());
            adapter.filterList(filteredList);
        }
    }
    private void filterDataa(List<String> apartmentNames) {
        ArrayList<RoomDetailsClass> filteredList = new ArrayList<>();
        for (RoomDetailsClass roomDetails : datalist) {
            // Check if the room belongs to one of the selected apartment names
            if (apartmentNames.contains(roomDetails.getApName())) {
                filteredList.add(roomDetails);
            }
        }
        // Update RecyclerView adapter with filtered data
        adapter.filterList(filteredList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseReference != null && valueEventListener != null) {
            databaseReference.removeEventListener(valueEventListener);
        }
//>>>>>>> d10b120a82efb2e733cde0bf32e034d2bd3da674
    }
}
//>>>>>>> 1fa2790b91dd183859b7cfd76c59c5cdc53e448a
