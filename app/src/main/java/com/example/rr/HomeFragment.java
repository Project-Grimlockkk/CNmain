package com.example.rr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.Collections;
import java.util.Comparator;
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

    RecyclerView recyclerView1;
    List<RoomDetailsClass> datalist;
    recyclerContactAdapter adapter;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        Button addingPGButton = rootView.findViewById(R.id.addingPG);
        addingPGButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InputActivity.class);
                startActivity(intent);
            }
        });

        recyclerView1 = rootView.findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView1.setLayoutManager(gridLayoutManager);

        datalist = new ArrayList<>();
        adapter = new recyclerContactAdapter(getActivity(), datalist);
        recyclerView1.setAdapter(adapter);

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
            Collections.sort(filteredList, new RoomDetailsComparator());
            adapter.filterList(filteredList);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseReference != null && valueEventListener != null) {
            databaseReference.removeEventListener(valueEventListener);
        }
    }
}
