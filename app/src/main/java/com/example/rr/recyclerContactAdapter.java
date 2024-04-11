package com.example.rr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
//<<<<<<< HEAD
    List<RoomDetailsClass> arrDetails;

//    private List<RoomDetailsClass> filteredList;
//    ArrayList<Property>arrProperty;

//    recyclerContactAdapter(Context context, List<RoomDetailsClass>arrDetails){
//        this.context=context;
//        this.arrDetails=arrDetails;
//        this.filteredList = new ArrayList<>(arrDetails);
//=======
//    private List<RoomDetailsClass> arrDetails;
    List<RoomDetailsClass> arrDetailsFiltered;

    recyclerContactAdapter(Context context, List<RoomDetailsClass> arrDetails) {
        this.context = context;
        this.arrDetails = arrDetails;
        this.arrDetailsFiltered = new ArrayList<>(arrDetails);
    }

    public void filterList(ArrayList<RoomDetailsClass> filteredList) {
        arrDetailsFiltered = filteredList;
        notifyDataSetChanged(); // Notify adapter of data change
//>>>>>>> 1fa2790b91dd183859b7cfd76c59c5cdc53e448a
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_card2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//<<<<<<< HEAD
//        RoomDetailsModel roomDetails = arrDetails.get(position);
//        holder.apImg.setBackgroundResource(roomDetails.getApImageResource());
//        RoomDetailsClass roomDetails = filteredList.get(position);
//        Glide.with(context).load(arrDetails.get(position).getPgPhotos()).into(holder.apImg);
//        holder.apName.setText(arrDetails.get(position).getApName());
//        holder.apPrice.setText(arrDetails.get(position).getRentInr());
//        holder.apVacancy.setText(arrDetails.get(position).getVaccancy());
//        holder.gender.setText(arrDetails.get(position).getGender());
//        holder.apDistance.setText(arrDetails.get(position).getDistance());
//=======
        RoomDetailsClass roomDetails = arrDetailsFiltered.get(position);
        Glide.with(context).load(roomDetails.getPgPhotos()).into(holder.apImg);
        holder.apName.setText(roomDetails.getApName());
        holder.apPrice.setText(roomDetails.getRentInr());
        holder.apVacancy.setText(roomDetails.getVaccancy());
        holder.gender.setText(roomDetails.getGender());
        holder.apDistance.setText(roomDetails.getDistance());
//>>>>>>> 1fa2790b91dd183859b7cfd76c59c5cdc53e448a

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//<<<<<<< HEAD
                Intent intent=new Intent(context, pg_info2.class);
//                RoomDetailsClass selectedRoom = filteredList.get(holder.getAdapterPosition());
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
                intent.putExtra("pgOwner", arrDetails.get(holder.getAdapterPosition()).getPgOwner());
                intent.putExtra("deposit", arrDetails.get(holder.getAdapterPosition()).getDeposit());
//=======
//                Intent intent = new Intent(context, pg_info2.class);
//                intent.putExtra("pgPhotos", roomDetails.getPgPhotos());
//                intent.putExtra("apName", roomDetails.getApName());
//                intent.putExtra("Vacancy", roomDetails.getVaccancy());
//                intent.putExtra("rentInr", roomDetails.getRentInr());
//                intent.putExtra("distance", roomDetails.getDistance());
//                intent.putExtra("gender", roomDetails.getGender());
//                intent.putExtra("address", roomDetails.getAddress());
//                intent.putExtra("phoneNo", roomDetails.getPhoneNo());
//                intent.putExtra("electricity", roomDetails.getElectricity());
//                intent.putExtra("waterSupply", roomDetails.getWaterSupply());
//                intent.putExtra("cleaningFacility", roomDetails.getCleaningFacility());
//                intent.putExtra("pgOwner", roomDetails.getPgOwner());
//                intent.putExtra("deposit", roomDetails.getDeposit());
//>>>>>>> 1fa2790b91dd183859b7cfd76c59c5cdc53e448a

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrDetailsFiltered.size();
    }

//    public void filterList(String genderFilter) {
//        filteredList.clear();
//        if (genderFilter.equals("All")) {
//            filteredList.addAll(arrDetails);
//        } else {
//            for (RoomDetailsClass room : arrDetails) {
//                if (room.getGender().equals(genderFilter)) {
//                    filteredList.add(room);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView apName, apPrice, apVacancy, apDistance, gender;
        ImageView  vcImg, distImg, apImg;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            apName = itemView.findViewById(R.id.apName);
            gender = itemView.findViewById(R.id.gender);
            apPrice = itemView.findViewById(R.id.apPrice);
            apVacancy = itemView.findViewById(R.id.apVacancy);
            apDistance = itemView.findViewById(R.id.apDistance);
            vcImg = itemView.findViewById(R.id.vcImg);
            distImg = itemView.findViewById(R.id.distImg);
            apImg = itemView.findViewById(R.id.apImg);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
