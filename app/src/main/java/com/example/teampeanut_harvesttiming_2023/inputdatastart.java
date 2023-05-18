package com.example.teampeanut_harvesttiming_2023;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.navigation.ui.AppBarConfiguration;

import android.widget.Button;
import android.widget.Spinner;


import com.example.teampeanut_harvesttiming_2023.data.User;
import com.example.teampeanut_harvesttiming_2023.databinding.ActivityInputdatastartBinding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class inputdatastart extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private String crop, variety, soil, fert;
    Button toMap;
    public static LatLng Farm;
    TextInputEditText addressInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




        com.example.teampeanut_harvesttiming_2023.databinding.ActivityInputdatastartBinding binding = ActivityInputdatastartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//crop drop down
        setSupportActionBar(binding.toolbar);

        Spinner cropSpinner = (Spinner) findViewById(R.id.crop_dropdown_menu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.crops_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cropSpinner.setAdapter(adapter);
//Narrowing for crop variety
        //pulls what was selected
        Spinner varietySpinner = (Spinner) findViewById(R.id.variety_dropdown_menu);

    cropSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String cropSelected = cropSpinner.getSelectedItem().toString();
            if (cropSelected.equals("Corn")) {

                ArrayAdapter<CharSequence> varietyAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.cVariety_array, android.R.layout.simple_spinner_item);
                varietyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                varietySpinner.setAdapter(varietyAdapter);
            } else {

                ArrayAdapter<CharSequence> varietyAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sbVariety_array, android.R.layout.simple_spinner_item);
                varietyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                varietySpinner.setAdapter(varietyAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


    });

        Spinner soilSpinner = (Spinner) findViewById(R.id.soil_dropdown_menu);
        ArrayAdapter<CharSequence> soilAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.soil_type_array, android.R.layout.simple_spinner_item);
        soilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        soilSpinner.setAdapter(soilAdapter);

        Spinner fertSpinner = (Spinner) findViewById(R.id.fert_dropdown_menu);
        ArrayAdapter<CharSequence> fertAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.fert_array, android.R.layout.simple_spinner_item);
        fertAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fertSpinner.setAdapter(fertAdapter);


                toMap = (Button)findViewById(R.id.next_but);
        toMap.setOnClickListener(new View.OnClickListener()

        {


            @Override
            public void onClick(View v) {
                crop = cropSpinner.getSelectedItem().toString();

                variety = varietySpinner.getSelectedItem().toString();

                soil = soilSpinner.getSelectedItem().toString();
                fert = fertSpinner.getSelectedItem().toString();

                DatabaseReference user = database.getReference("User");
                //user.setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                User newUser = new User(crop ,variety);
                user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newUser);

                addressInput = findViewById(R.id.farmLong);
                String location = addressInput.getText().toString();
                List<Address> addressList = null;
                if (location != null || location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(inputdatastart.this);
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                        Log.i("Nikolas", "address list" + addressList.toString());
                    } catch (IOException e) {
                        Log.i("Nikolas", "this be doing numbers");
                        e.printStackTrace();
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    Address address = addressList.get(0);

                    Farm = new LatLng(address.getLatitude(), address.getLongitude());
                }

                startActivity(new Intent(getApplicationContext(), MapSelection.class));






           /* if you want to finish the first activity then just call
            finish(); */

            }
        });



    }


}