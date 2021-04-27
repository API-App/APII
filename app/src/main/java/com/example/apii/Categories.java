package com.example.apii;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.apii.Models.API;
import com.example.apii.Models.Category;
import com.example.apii.adapters.ApiAdapter;
import com.example.apii.adapters.CategoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Categories extends Fragment {

    public static final String TAG = "ApiStream";
    public static final String CATEGORIES_ENTRIES_URL = "https://api.publicapis.org/categories";
    private RecyclerView rvCategories;
    private CategoryAdapter adapter;
    private List<Category> categories;


    public Categories() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCategories = view.findViewById(R.id.rvCategories);
        categories = new ArrayList<>();
        adapter = new CategoryAdapter(getContext(),categories);

        rvCategories.setAdapter(adapter);
        rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        makeRequest();
    }

    private void makeRequest(){
        // Creating a client to make network requests
        AsyncHttpClient client = new AsyncHttpClient();

        // Make request for all API entries
        // Store in List<API> apis
        client.get(CATEGORIES_ENTRIES_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONArray results = json.jsonArray;
                categories.addAll(Category.fromJsonArray(results));
                adapter.setAllCategories(categories);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}