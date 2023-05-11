package com.example.teampeanut_harvesttiming_2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

public class FarmOverviewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private Button backToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_overview);
        backToMenu = (Button)findViewById(R.id.backtothepast);
        backToMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Menu.class));
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(inputdatastart.Farm, 15);
        gMap.animateCamera(cameraUpdate);


    }
}