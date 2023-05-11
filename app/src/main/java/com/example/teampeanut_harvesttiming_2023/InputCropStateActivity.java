package com.example.teampeanut_harvesttiming_2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.teampeanut_harvesttiming_2023.data.NewData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;


public class InputCropStateActivity extends AppCompatActivity {
    // One Button
    Button BSelectImage;
    FrameLayout frameLayout;
    // One Preview Image
    ImageView IVPreviewImage;
    EditText soilTemp;
    EditText moist;
    Button inputBut;
    private static final int CAMERA_REQUEST = 1888;
    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userNewData = database.getReference("User Update Data");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_crop_state);
        soilTemp = (EditText) findViewById(R.id.soilTempInput);
        moist = (EditText) findViewById(R.id.moistureInput);
        inputBut = findViewById(R.id.inputCropButton);
        //Pass Data on Button Click

        inputBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get data from input field

                String moisture = moist.getText().toString();
                String soilTemperature = soilTemp.getText().toString();
                NewData newData = new NewData(moisture, soilTemperature);

                Intent intent = new Intent(InputCropStateActivity.this, MonitorData.class);
                if (newData != null) {
                    userNewData.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newData);
                    startActivity(intent);
                } else {
                    startActivity(intent);
                }

            }
        });


        IVPreviewImage = findViewById(R.id.CropImage);


        // this function is triggered when
        // the Select Image Button is clicked


        // this function is triggered when user
        // selects the image from the imageChooser
    }

    /**
     * @param event Button event
     */

    public void photo(View event) throws IOException {
        Log.i("Photo", "Button pressed");
        // create and open intent
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    // This method gets called on an activity result (IE, returning from taking a photo)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) { // This makes sure its coming back from the right activity and that everything went according to plan
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data"); // get a bitmap of the photo
                IVPreviewImage.setImageBitmap(photo);
                File file = new File(this.getBaseContext().getFilesDir(), "photo.png"); // create file object
                FileOutputStream out = new FileOutputStream(file); // create output stream
                photo.compress(Bitmap.CompressFormat.PNG, 3, out); // save bitmap to file
                Log.i("Photo", "Path: " + file.getPath()); // print out file
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}