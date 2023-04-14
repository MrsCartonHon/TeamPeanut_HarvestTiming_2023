package com.example.teampeanut_harvesttiming_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MonitorData extends AppCompatActivity {

    TextView soilTemp, moisture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_data);

        soilTemp = findViewById(R.id.soilTempDisplay);
        moisture = findViewById(R.id.moistureDisplay);
        //Get text from Intent
        Intent intent = getIntent();
        String getName = intent.getStringExtra("name");
        String getNumber = intent.getStringExtra("number");
        //Set Text
        soilTemp.setText(getName);
        moisture.setText(getNumber);

        Button backButton = (Button) findViewById(R.id.MoniterBackButton);
        backButton.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Menu.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });

    }
}