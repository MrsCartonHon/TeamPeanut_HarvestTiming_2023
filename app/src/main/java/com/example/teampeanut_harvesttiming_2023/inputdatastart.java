package com.example.teampeanut_harvesttiming_2023;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import androidx.navigation.ui.AppBarConfiguration;
import android.widget.Spinner;


import com.example.teampeanut_harvesttiming_2023.databinding.ActivityInputdatastartBinding;

public class inputdatastart extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityInputdatastartBinding binding;
    Spinner cropSpinner=findViewById(R.id.crop_dropdown);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInputdatastartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.crops, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        cropSpinner.setAdapter(adapter);
    }
}