package com.example.rr;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learn.R;

import java.util.ArrayList;

public class listingpage extends AppCompatActivity {
    ArrayList<RoomDetailsModel> arrDetails =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listingpage);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        arrDetails.add(new RoomDetailsModel(R.drawable.bg, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Harishankar Apartment", " 700m", "2", "RS 2000"));
        arrDetails.add(new RoomDetailsModel(R.drawable.bg, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Harishankar Apartment", " 700m", "2", "RS 2000"));
        arrDetails.add(new RoomDetailsModel(R.drawable.bg, R.drawable.baseline_man_24, R.drawable.distance_to_travel_between_two_points_svgrepo_com, "Harishankar Apartment", " 700m", "2", "RS 2000"));



        recyclerContactAdapter adapter = new recyclerContactAdapter(this, arrDetails);
        recycler.setAdapter(adapter);



    }
}