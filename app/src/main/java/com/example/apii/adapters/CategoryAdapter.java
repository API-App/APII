package com.example.apii.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apii.Models.API;
import com.example.apii.Models.Category;
import com.example.apii.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

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
        View categoryView = LayoutInflater.from(context).inflate(R.layout.item_api, parent, false);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bind(Category category) {
            tvTitle.setText(category.getCategory());
        }
    }

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories.addAll(allCategories);
    }
}
