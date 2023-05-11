package com.example.teampeanut_harvesttiming_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teampeanut_harvesttiming_2023.model.MyWeather;

public class Menu extends AppCompatActivity {

        private Button toNewHarvest, buttonWeather, openMonitor, openUpdateCropState, openMapOverview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toNewHarvest = (Button)findViewById(R.id.newHarvest);
        toNewHarvest.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), StartSelect.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });
        buttonWeather = (Button)findViewById(R.id.toWeather);
        buttonWeather.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Menu.this, WeatherActivity.class));
           /* if you want to finish the first activity then just call
            finish(); */


            }
        });
        openMonitor = (Button)findViewById(R.id.toMonitor);
        openMonitor.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MonitorData.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });
        openUpdateCropState = (Button)findViewById(R.id.toUpdateCropState);
        openUpdateCropState.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), InputCropStateActivity.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });

        openMapOverview = (Button)findViewById(R.id.toMapOverview);
        openMapOverview.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), FarmOverviewActivity.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });

    }
}