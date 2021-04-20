package com.example.apii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.apii.Models.API;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static final String APII_CATEGORIES_URL = "https://api.publicapis.org/categories";
    public static final String TAG = "Main Activity";
    private NavController navController;
    List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Creating a client to make network requests
        AsyncHttpClient client = new AsyncHttpClient();

        // Make request for categories
        // Store in List<String> categories
        client.get(APII_CATEGORIES_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("entries");
                    Log.i(TAG, "Number of results: " + results.length());
                    setUpNavMenu(navigationView, drawer, API.fromJsonArray(results));
                    List<API> apis = API.fromJsonArray(results);
                } catch (JSONException e) {
                    Log.e(TAG, "Hit a json exception ", e);
                }
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

    public void setUpNavMenu(NavigationView navigationView, DrawerLayout drawer, List<API> apis) {
        // Creates the menu icon in each fragment
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_api_stream, R.id.nav_categories)
                .setDrawerLayout(drawer)
                .build();

         navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nav_random) {
                    controller.popBackStack();
                    API rand_api = apis.get((int) (Math.random() * apis.size() - 1));
                    Log.d(TAG, rand_api.getTitle());
                    // TODO: Use this code in API stream code to navigate to a detail view
                    // in other areas you need: Bundle arguments = new Bundle();
                    arguments.putParcelable("api", Parcels.wrap(rand_api));
                    navController.navigate(R.id.nav_detail_view, arguments);
                }
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