package com.example.rr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learn.R;

import java.util.ArrayList;

public class recyclerContactAdapter extends RecyclerView.Adapter<recyclerContactAdapter.ViewHolder> {

    Context context;
    ArrayList<RoomDetailsModel>arrDetails;

    recyclerContactAdapter(Context context, ArrayList<RoomDetailsModel>arrDetails){
        this.context=context;   
        this.arrDetails=arrDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.listing_card2, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RoomDetailsModel roomDetails = arrDetails.get(position);
        holder.apImg.setBackgroundResource(roomDetails.getApImageResource());
        holder.apName.setText(arrDetails.get(position).apName);
        holder.apPrice.setText(arrDetails.get(position).price);
        holder.apVacancy.setText(arrDetails.get(position).vacancy);
        holder.gender.setText(arrDetails.get(position).gender);
        holder.apDistance.setText(arrDetails.get(position).distance);
        holder.vcImg.setImageResource(arrDetails.get(position).vacancyImg);
        holder.distImg.setImageResource(arrDetails.get(position).distanceImg);
    }

    @Override
    public int getItemCount() {
        return arrDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView apName, apPrice, apVacancy, apDistance, gender;
        ImageView  vcImg, distImg,apImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            apName=itemView.findViewById(R.id.apName);
            gender=itemView.findViewById(R.id.gender);
            apPrice=itemView.findViewById(R.id.apPrice);
            apVacancy=itemView.findViewById(R.id.apVacancy);
            apDistance=itemView.findViewById(R.id.apDistance);
            vcImg=itemView.findViewById(R.id.vcImg);
            distImg=itemView.findViewById(R.id.distImg);
            apImg=itemView.findViewById(R.id.apImg);
        }
    }
}
