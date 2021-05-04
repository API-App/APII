package com.example.apii;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.apii.Models.API;
import com.example.apii.adapters.ApiAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApiStream extends Fragment {

    public static final String TAG = "ApiStream";
    public static final String APII_ENTRIES_URL = "https://api.publicapis.org/entries";
    public static final String APII_CATEGORIES_URL = "https://api.publicapis.org/entries?category=";
    private RecyclerView rvApis;
    private ApiAdapter adapter;
    private List<API> apis;
    String selectedCat;

    public ApiStream() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_api_stream, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvApis = view.findViewById(R.id.rvApis);
        apis = new ArrayList<>();
        adapter = new ApiAdapter(getContext(),apis);
        rvApis.setAdapter(adapter);
        rvApis.setLayoutManager(new LinearLayoutManager(getContext()));
        rvApis.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        try {
            selectedCat = getArguments().getString("selectedCat");
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(selectedCat);
            makeRequest(selectedCat);
        } catch(NullPointerException e){
            makeRequest();
        }

    }

    private void makeRequest(){
        // Creating a client to make network requests
        AsyncHttpClient client = new AsyncHttpClient();

        // Make request for all API entries
        // Store in List<API> apis
        client.get(APII_ENTRIES_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("entries");
                    apis.addAll(API.fromJsonArray(results));
                    adapter.setAllApis(apis);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "Hit a json exception ", e);
                }

            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    private void makeRequest(String selectedCat){
        // Creating a client to make network requests
        AsyncHttpClient client = new AsyncHttpClient();

        // Make request for all API entries
        // Store in List<API> apis
        client.get(APII_CATEGORIES_URL+selectedCat, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("entries");
                    apis.addAll(API.fromJsonArray(results));
                    adapter.setAllApis(apis);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "Hit a json exception ", e);
                }

            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    // Adds search to the menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.api_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search_api).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}