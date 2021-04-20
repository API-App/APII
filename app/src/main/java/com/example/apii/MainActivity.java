package com.example.apii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.apii.Models.API;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static final String APII_ENTRIES_URL = "https://api.publicapis.org/entries";
    public static final String APII_CATEGORIES_URL = "https://api.publicapis.org/categories";
    public static final String TAG = "Main Activity";
    List<API> apis;
    List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Creates the menu icon in each fragment
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_api_stream, R.id.nav_categories)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

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
                    apis = API.fromJsonArray(results);
                    Log.i(TAG, "API Len: " + String.valueOf(apis.size()));
                } catch (JSONException e) {
                    Log.e(TAG, "Hit a json exception ", e);
                }

            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

        // Make request for categories
        // Store in List<String> categories
        client.get(APII_CATEGORIES_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONArray results = json.jsonArray;
                categories = fromJsonArray(results);
                Log.i(TAG, "Category Len: " + String.valueOf(categories.size()));
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

    }

    private List<String> fromJsonArray(JSONArray results) {
        List<String> categories = new ArrayList<>() ;
        for(int i=0;i<results.length();i++){
            try{
                categories.add(results.getString(i));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return categories;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}