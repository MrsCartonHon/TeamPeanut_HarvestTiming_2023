package com.example.teampeanut_harvesttiming_2023;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.navigation.ui.AppBarConfiguration;

import android.widget.Button;
import android.widget.Spinner;


import com.example.teampeanut_harvesttiming_2023.data.User;
import com.example.teampeanut_harvesttiming_2023.databinding.ActivityInputdatastartBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class inputdatastart extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private String crop, variety, soil, fert;
    Button toMap;
    public static LatLng Farm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();





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
                User newUser = new User(crop ,variety, soil, fert);
                user.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newUser);





                startActivity(new Intent(getApplicationContext(), MapSelection.class));






           /* if you want to finish the first activity then just call
            finish(); */

            }
        });



    }


}