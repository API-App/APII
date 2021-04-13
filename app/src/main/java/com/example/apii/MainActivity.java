package com.example.apii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        setContentView(R.layout.activity_main);
        DrawerLayout drawer = findViewById(R.id.drawer);

        // Creates the menu icon in each fragment
        //mAppBarConfiguration = new AppBarConfiguration.Builder(
        //        (R.id.nav_view,R.id.).setDrawerLayout(drawer).build();
        //);
    }
}