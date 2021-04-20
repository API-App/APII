package com.example.apii.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apii.Models.API;
import com.example.apii.R;

import java.util.List;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ViewHolder>{

    Context context;
    List<API> apis;

    public ApiAdapter(Context context, List<API> apis) {
        this.context = context;
        this.apis = apis;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View apiView = LayoutInflater.from(context).inflate(R.layout.item_api, parent, false);
        return new ViewHolder(apiView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the api at the position
        API api = apis.get(position);
        // Bind the api title to VH
        holder.bind(api);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return apis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bind(API api) {
            tvTitle.setText(api.getTitle());
        }
    }
}
