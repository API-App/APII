package com.example.apii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String APII_ENTRIES_URL = "https://api.publicapis.org/entries";
    public static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating a client to make network requests
        AsyncHttpClient client = new AsyncHttpClient();

        // I could put this in a separate method to make the request
        // makeRequest(client, ULR)
        client.get(APII_ENTRIES_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("entries");
                    Log.i(TAG, "Number of results: " + results.length());
                } catch (JSONException e) {
                    Log.e(TAG,"Hit a json exception ", e);
                }

            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

    }
}