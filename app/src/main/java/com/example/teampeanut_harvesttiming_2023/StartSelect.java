package com.example.teampeanut_harvesttiming_2023;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartSelect extends AppCompatActivity {

    private Button toInputDatajav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activety_startselect);

        toInputDatajav = (Button)findViewById(R.id.toInputData);
        toInputDatajav.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),inputdatastart.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });

    }
}