package com.example.apii.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apii.Models.Category;
import com.example.apii.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements Filterable {

    Context context;
    List<Category> categories;
    private List<Category> allCategories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        // List of all APIs for filtering and searching
        allCategories = new ArrayList<Category>(categories);
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryView = LayoutInflater.from(context).inflate(R.layout.item_cat, parent, false);
        return new ViewHolder(categoryView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        // Get the api at the position
        Category category = categories.get(position);
        // Bind the api title to VH
        holder.bind(category);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bind(Category category) {
            tvTitle.setText(category.getCategory());
            container = itemView.findViewById(R.id.container);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
                    Bundle arguments = new Bundle();
                    arguments.putString("selectedCat",category.getCategory());
                    navController.navigate(R.id.nav_api_stream, arguments);
                }
            });
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
            List<Category> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(search.isEmpty()) {
                results.values = allCategories;
                return results;
            }

            for(Category cat : allCategories) {
                if (cat.getCategory().toLowerCase().contains(search.toLowerCase()))
                    filteredList.add(cat);
            }
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            categories.clear();
            categories.addAll((Collection<? extends Category>) results.values);
            notifyDataSetChanged();
        }
    };

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories.addAll(allCategories);
    }
}
