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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.collection.LLRBNode;
import com.google.type.Color;

import java.util.ArrayList;
import java.util.List;

public class MapSelection extends AppCompatActivity implements OnMapReadyCallback {

    private Button toMenu;
    private Button commitButton;
    private Button restartButton;
    private GoogleMap gMap;
    private static final int COLOR_LIGHT_GREEN_ARGB = 0xff81C784;
    private static final int COLOR_DARK_GREEN_ARGB = 0xff388E3C;
    static Boolean drawing = false;
    public static List<PolygonOptions> finalFields = new ArrayList<>();

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
                    if(drawing) {
                        polygon.add(latLng);
                    }
                }
            });

            commitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(drawing) {
                        PolygonOptions cropField = new PolygonOptions();
                        for (int x = 0; x < polygon.size(); x = x + 1) {
                            cropField.add(polygon.get(x));
                        }
                        cropField.add(polygon.get(0));
                        cropField.fillColor(COLOR_LIGHT_GREEN_ARGB);
                        cropField.strokeColor(COLOR_DARK_GREEN_ARGB);
                        cropField.clickable(true);
                        Polygon finalField = gMap.addPolygon(cropField);
                        Boolean sizeCheck = polygon.size() == 0;
                        if(!sizeCheck){
                            finalFields.add(cropField);
                            Log.i("nikoas", finalFields.toString());
                        }
                        polygon.clear();
                    }
                }
            });




        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawing) {
                    drawing = true;
                }
            }
        });

    }

}