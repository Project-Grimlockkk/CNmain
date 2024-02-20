package com.example.rr;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerAdaptor extends RecyclerView.Adapter<recyclerAdaptor> {

    @NonNull
    @Override
    public recyclerAdaptor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdaptor holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public ViewHolder(View itemView){
            super(itemView);
        }
    }
}
