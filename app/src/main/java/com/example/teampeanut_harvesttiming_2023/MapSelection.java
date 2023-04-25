package com.example.teampeanut_harvesttiming_2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapSelection extends AppCompatActivity implements OnMapReadyCallback {

    private Button toMenu;
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_selection);

        toMenu = (Button) findViewById(R.id.next);
        toMenu.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Menu.class));
           /* if you want to finish the first activity then just call
            finish(); */

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
        LatLng farm = new LatLng(41.557579, -90.495911);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(farm, 15);
        gMap.animateCamera(cameraUpdate);
    }

}