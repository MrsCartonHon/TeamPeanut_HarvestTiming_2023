package com.example.teampeanut_harvesttiming_2023;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.teampeanut_harvesttiming_2023.databinding.ActivityInputdatastartBinding;

public class inputdatastart extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityInputdatastartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInputdatastartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        


    }
}