package com.example.teampeanut_harvesttiming_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teampeanut_harvesttiming_2023.R;
import com.example.teampeanut_harvesttiming_2023.ui.login.JDSignIn;

public class SignUpPage extends AppCompatActivity {
    private Button backLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        backLogin = (Button)findViewById(R.id.backToLogin);
        backLogin.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), JDSignIn.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });
    }
}