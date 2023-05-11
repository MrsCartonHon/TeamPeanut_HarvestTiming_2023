package com.example.teampeanut_harvesttiming_2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.firebase.database.collection.LLRBNode;
import com.google.type.Color;

import java.util.ArrayList;
import java.util.List;

public class MapSelection extends AppCompatActivity implements OnMapReadyCallback {

    private Button toMenu;
    private Button commitButton;
    private Button restartButton;
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_selection);

        toMenu = (Button) findViewById(R.id.next);
        commitButton = findViewById(R.id.Commit);
        restartButton = findViewById(R.id.Restart);
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
        List<LatLng> polygon = new ArrayList<>();
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(inputdatastart.Farm, 15);
        gMap.animateCamera(cameraUpdate);

        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                polygon.add(latLng);
            }
        });

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PolygonOptions cropField = new PolygonOptions();
                for (int x = 0; x < polygon.size(); x = x + 1) {
                    cropField.add(polygon.get(x));
                }
                cropField.add(polygon.get(0));
                Polygon finalField = gMap.addPolygon(cropField);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                polygon.clear();
            }
        });
    }

}