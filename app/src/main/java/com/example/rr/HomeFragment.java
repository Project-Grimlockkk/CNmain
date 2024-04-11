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
import java.util.List;

public class HomeFragment extends Fragment {

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
        searchView = rootView.findViewById(R.id.search);
        adapter = new recyclerContactAdapter(getActivity(), datalist); // Initialize adapter
        recyclerView1.setAdapter(adapter); // Set adapter to RecyclerView

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
}
