package com.example.apii;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
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
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

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
}