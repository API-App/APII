package com.example.apii;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apii.Models.API;

import org.json.JSONObject;
import org.parceler.Parcels;

public class DetailView extends Fragment {

    private static String TAG = "DetailView";
    private static String str;

    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvLink;
    private TextView tvCat;

    private TextView tvAuth;
    private TextView tvHttps;
    private TextView tvCors;

    private API api;

    public DetailView() {
        // Required empty public constructor
    }

    // Function to create new APIs
    public static DetailView newInstance(API api) {
        DetailView fragment = new DetailView();
        Bundle args = new Bundle();
        args.putParcelable("api", Parcels.wrap(api));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        api = Parcels.unwrap(getArguments().getParcelable("api"));
        tvTitle = view.findViewById(R.id.tv_title);
        tvDescription = view.findViewById(R.id.tv_description);
        tvLink = view.findViewById(R.id.tv_link);
        tvAuth = view.findViewById(R.id.tv_Auth);
        tvCors = view.findViewById(R.id.tv_cors);
        tvHttps = view.findViewById(R.id.tv_https);
        tvCat = view.findViewById(R.id.tv_cat);

        tvCat.setText(api.getCategory());
        tvTitle.setText(api.getTitle());
        tvLink.setText(api.getLink());
        tvDescription.setText(api.getDescription());
        tvCors.setText(api.getCors());
        String auth = api.getAuth();
        if(!auth.equals("")) {
            tvAuth.setText(auth);
        } else {
            tvAuth.setText("None");
        }
        if(api.getHttps()) {
            tvHttps.setText("yes");
        }else {
            tvHttps.setText("no");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_view, container, false);
    }

    // Creates the menu for the share button
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.share_api) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT,api.getTitle() + ":\n" + api.getLink());
            startActivity(Intent.createChooser(share, "Share using"));
        }
        return super.onOptionsItemSelected(item);
    }
}