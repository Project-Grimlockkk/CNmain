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
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    RadioButton filterBoys, filterGirls, filterVeg, filterNonveg, filterVeryClose, filterMedDistance;
    RadioGroup filterGroup;

    RecyclerView recyclerView1;
    List<RoomDetailsClass> datalist;
    ArrayList<RoomDetailsModel> arrDetails = new ArrayList<>();
    ValueEventListener valueEventListener;
    private ProgressDialog loadingDialog;
    DatabaseReference databaseReference;
    SearchView searchView;
    recyclerContactAdapter adapter; // Declare adapter as a member variable

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
        apartmentNamesList.add(Arrays.asList("Asher Villa", "Flat","Room", "Shivdatt Apartment"));
        apartmentNamesList.add(Arrays.asList("Silver Oak Apartment", "Sahara Boys Hostel"));
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
                // Start the InputActivity
                Intent intent = new Intent(getActivity(), InputActivity.class);
                startActivity(intent);
            }
        });

        recyclerView1 = rootView.findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),1);
        recyclerView1.setLayoutManager(gridLayoutManager);

        datalist = new ArrayList<>();

//        recyclerContactAdapter adapter = new recyclerContactAdapter(getActivity(), datalist);
//        recyclerView1.setAdapter(adapter);

        adapter = new recyclerContactAdapter(getActivity(), datalist); // Initialize adapter
        recyclerView1.setAdapter(adapter);   // Set adapter to RecyclerView

        databaseReference= FirebaseDatabase.getInstance().getReference("PGs");

        valueEventListener= databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    String apName= itemSnapshot.getKey();
                    String key= itemSnapshot.getKey();
                    String parentKey = snapshot.getKey();
                    Log.d("FirebaseKey", "Parent Key: " + parentKey);
                    Log.d("FirebaseKey", "Key: " + key);

                    for(DataSnapshot childSnapshot: itemSnapshot.getChildren()){
                        String grandChildKey = childSnapshot.getKey();
                        Log.d("FirebaseKey", "Grandchild Key: " + grandChildKey);
                    }
                    RoomDetailsClass roomDetailsClass= itemSnapshot.getValue(RoomDetailsClass.class);
                    datalist.add(roomDetailsClass);
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
            }
        });

        searchView = rootView.findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search when user submits query
                filterData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Perform search as user types
                filterData(newText);
                return true;
            }
        });

        return rootView;
    }
//<<<<<<< HEAD
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

//=======

    private void filterData(String query) {
        if (adapter != null) {
            ArrayList<RoomDetailsClass> filteredList = new ArrayList<>();
            for (RoomDetailsClass roomDetails : datalist) {
                if (roomDetails.getApName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(roomDetails);
                }
            }
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
}
//>>>>>>> 1fa2790b91dd183859b7cfd76c59c5cdc53e448a
