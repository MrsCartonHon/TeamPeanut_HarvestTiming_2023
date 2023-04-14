package com.example.teampeanut_harvesttiming_2023;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.renderscript.Sampler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.navigation.ui.AppBarConfiguration;

import android.widget.Button;
import android.widget.Spinner;


import com.example.teampeanut_harvesttiming_2023.databinding.ActivityInputdatastartBinding;
import com.example.teampeanut_harvesttiming_2023.ui.login.JDSignIn;

public class inputdatastart extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    Button toMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.teampeanut_harvesttiming_2023.databinding.ActivityInputdatastartBinding binding = ActivityInputdatastartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toMap = (Button)findViewById(R.id.next_but);
        toMap.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MapSelection.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });

//crop drop down
        setSupportActionBar(binding.toolbar);

        Spinner cropSpinner = (Spinner) findViewById(R.id.crop_dropdown_menu);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.crops_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cropSpinner.setAdapter(adapter);
//Narrowing for crop variety
        //pulls what was selected
    cropSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String newItem = cropSpinner.getSelectedItem().toString();
            if (newItem.equals("Corn")) {
                Spinner varietySpinnerC = (Spinner) findViewById(R.id.variety_dropdown_menu);
                ArrayAdapter<CharSequence> varietyAdapterC = ArrayAdapter.createFromResource(getApplicationContext(), R.array.cVariety_array, android.R.layout.simple_spinner_item);
                varietyAdapterC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                varietySpinnerC.setAdapter(varietyAdapterC);
            } else {
                Spinner varietySpinnerSB = (Spinner) findViewById(R.id.variety_dropdown_menu);
                ArrayAdapter<CharSequence> varietyAdapterSB = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sbVariety_array, android.R.layout.simple_spinner_item);
                varietyAdapterSB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                varietySpinnerSB.setAdapter(varietyAdapterSB);
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


    }


}