package com.example.teampeanut_harvesttiming_2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teampeanut_harvesttiming_2023.data.NewData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MonitorData extends AppCompatActivity {

    TextView soilTemp, moisture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_data);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        File file = new File(this.getBaseContext().getFilesDir(), "photo.png");

        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

        ImageView myImage = (ImageView) findViewById(R.id.CropImage);

        myImage.setImageBitmap(myBitmap);

        soilTemp = findViewById(R.id.soilTempDisplay);
        moisture = findViewById(R.id.moistureDisplay);

        DatabaseReference newDataRef = database.getReference("User Update Data");

        // Tell the class what to do when data changes in the database.
        newDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                refresh(snapshot.getChildren());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Data Fetching", error.getMessage());
            }
        });


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
    private void refresh(Iterable<DataSnapshot> userUpdateData){

        for(DataSnapshot user: userUpdateData) {
            if (user.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
            {
                NewData newUser = user.getValue(NewData.class);



                soilTemp.setText(newUser.getSoilTemp());
                moisture.setText(newUser.getMoisture());

            }
        }
        }
}