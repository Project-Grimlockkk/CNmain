package com.example.rr;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learn.R;

import java.util.ArrayList;

public class listingpage extends AppCompatActivity {
ArrayList<contactModel> arrRooms= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listingpage);

        RecyclerView recyclerView= findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrRooms.add( new contactModel(544, "room","2000","1.2km"));
        arrRooms.add( new contactModel(544, "room","2000","1.2km"));
        arrRooms.add( new contactModel(544, "room","2000","1.2km"));
        arrRooms.add( new contactModel(544, "room","2000","1.2km"));
        arrRooms.add( new contactModel(544, "room","2000","1.2km"));
        arrRooms.add( new contactModel(544, "room","2000","1.2km"));
        arrRooms.add( new contactModel(544, "room","2000","1.2km"));

    }
}