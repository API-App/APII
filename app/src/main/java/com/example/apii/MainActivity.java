package com.example.apii;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.apii.Models.API;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static final String APII_ENTRIES_URL = "https://api.publicapis.org/entries";
    public static final String APII_CATEGORIES_URL = "https://api.publicapis.org/categories";
    public static final String TAG = "Main Activity";
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);

        setUpNavWithRandom(navigationView, drawer);

    }

    private void setUpNavWithRandom(NavigationView navigationView, DrawerLayout drawer){
        // Creating a client to make network requests
        AsyncHttpClient client = new AsyncHttpClient();

        // Make request for all API entries
        // Store in List<API> apis
        client.get(APII_ENTRIES_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess: all entities");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("entries");
                    Log.i(TAG, "Number of results: " + results.length());
                    setUpNavMenu(navigationView, drawer, API.fromJsonArray(results));
                    List<API> apis = API.fromJsonArray(results);
                } catch (JSONException e) {
                    Log.e(TAG, "Hit a json exception ", e);
                }
            }
            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "OnFailure: all entities");
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}