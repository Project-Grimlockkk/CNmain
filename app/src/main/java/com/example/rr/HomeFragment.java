package com.example.rr;

import android.app.ProgressDialog;
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

    RecyclerView recyclerView1;
    List<RoomDetailsClass> datalist;

    ArrayList<RoomDetailsModel> arrDetails = new ArrayList<>();

    ValueEventListener valueEventListener;
    private ProgressDialog loadingDialog;
//    DatabaseReference databaseReference;

    DatabaseReference databaseReference;

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
//        recyclerView1.
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),1);
//        recycler.setHasFixedSize(true);
        recyclerView1.setLayoutManager(gridLayoutManager);

        datalist = new ArrayList<>();


        recyclerContactAdapter adapter = new recyclerContactAdapter(getActivity(), datalist);
        recyclerView1.setAdapter(adapter);

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

            }
        });

        

        return rootView;
    }
}