package com.example.rr;

import  android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.learn.R;

import java.util.ArrayList;
import java.util.List;

public class recyclerContactAdapter extends RecyclerView.Adapter<recyclerContactAdapter.ViewHolder> {

    private Context context;
    List<RoomDetailsClass> arrDetails;
//    ArrayList<Property>arrProperty;

    recyclerContactAdapter(Context context, List<RoomDetailsClass>arrDetails){
        this.context=context;   
        this.arrDetails=arrDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_card2, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        RoomDetailsModel roomDetails = arrDetails.get(position);
//        holder.apImg.setBackgroundResource(roomDetails.getApImageResource());
        Glide.with(context).load(arrDetails.get(position).getPgPhotos()).into(holder.apImg);
        holder.apName.setText(arrDetails.get(position).getApName());
        holder.apPrice.setText(arrDetails.get(position).getRentInr());
        holder.apVacancy.setText(arrDetails.get(position).getVaccancy());
        holder.gender.setText(arrDetails.get(position).getGender());
        holder.apDistance.setText(arrDetails.get(position).getDistance());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, pg_info2.class);
                intent.putExtra("pgPhotos", arrDetails.get(holder.getAdapterPosition()).getPgPhotos());
                intent.putExtra("apName", arrDetails.get(holder.getAdapterPosition()).getApName());
                intent.putExtra("Vacancy", arrDetails.get(holder.getAdapterPosition()).getVaccancy());
                intent.putExtra("rentInr", arrDetails.get(holder.getAdapterPosition()).getRentInr());
                intent.putExtra("distance", arrDetails.get(holder.getAdapterPosition()).getDistance());
                intent.putExtra("gender", arrDetails.get(holder.getAdapterPosition()).getGender());
                intent.putExtra("address", arrDetails.get(holder.getAdapterPosition()).getAddress());
                intent.putExtra("phoneNo", arrDetails.get(holder.getAdapterPosition()).getPhoneNo());
                intent.putExtra("electricity", arrDetails.get(holder.getAdapterPosition()).getElectricity());
                intent.putExtra("waterSupply", arrDetails.get(holder.getAdapterPosition()).getWaterSupply());
                intent.putExtra("cleaningFacility", arrDetails.get(holder.getAdapterPosition()).getCleaningFacility());

                context.startActivity(intent);

            }
        });
//        holder.vcImg.setImageResource(arrDetails.get(position).vacancyImg);
//        holder.distImg.setImageResource(arrDetails.get(position).distanceImg);
    }

    @Override
    public int getItemCount() {
        return arrDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView apName, apPrice, apVacancy, apDistance, gender;
        ImageView  vcImg, distImg,apImg;

        CardView cardView;
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
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
