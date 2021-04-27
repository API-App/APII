package com.example.apii.adapters;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apii.Models.API;
import com.example.apii.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.ViewHolder> implements Filterable {

    Context context;
    List<API> apis;
    private List<API> allApis;

    public ApiAdapter(Context context, List<API> apis) {
        this.context = context;
        this.apis = apis;
        // List of all APIs for filtering and searching
        allApis = new ArrayList<API>(apis);
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

    // Search functionality support
    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String search = constraint.toString();
            List<API> filteredList = new ArrayList<API>();
            FilterResults results = new FilterResults();

            if(search.isEmpty()) {
                results.values = allApis;
                return results;
            }

            for(API api : allApis) {
                if (api.getTitle().contains(search) || api.getDescription().contains(search))
                    filteredList.add(api);
            }
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            apis.clear();
            Log.d("eee", "Publish " + constraint + ((ArrayList)results.values).size());
            apis.addAll((Collection<? extends API>) results.values);
            notifyDataSetChanged();
        }
    };

    public void setAllApis(List<API> allApis) {
        this.allApis.addAll(allApis);
    }
}
