package com.example.teampeanut_harvesttiming_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.teampeanut_harvesttiming_2023.data.NewData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InputCropStateActivity extends AppCompatActivity {
    // One Button
    Button BSelectImage;

    // One Preview Image
    ImageView IVPreviewImage;
    EditText soilTemp;
    EditText moist;
    Button inputBut;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userNewData = database.getReference("User Update Data");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_crop_state);
        soilTemp = (EditText) findViewById(R.id.soilTempInput);
        moist = (EditText) findViewById(R.id.moistureInput);
        inputBut =  findViewById(R.id.inputCropButton);
        //Pass Data on Button Click
        inputBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data from input field
                String moisture = moist.getText().toString();
                String soilTemperature = soilTemp.getText().toString();
                NewData newData = new NewData(moisture, soilTemperature);
                userNewData.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newData);
                Intent intent = new Intent(InputCropStateActivity.this, MonitorData.class);

                startActivity(intent);
            }
        });

        // register the UI widgets with their appropriate IDs
        BSelectImage = findViewById(R.id.SelectImageButton);
        IVPreviewImage = findViewById(R.id.CropImage);

        // handle the Choose Image button to trigger
        // the image chooser function
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

//oncreate


    }


    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }
}