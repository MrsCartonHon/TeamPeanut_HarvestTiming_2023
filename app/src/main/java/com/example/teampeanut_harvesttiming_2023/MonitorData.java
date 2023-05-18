package com.example.teampeanut_harvesttiming_2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teampeanut_harvesttiming_2023.data.NewData;
import com.example.teampeanut_harvesttiming_2023.data.User;
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

    TextView soilTemp, moisture, days;
    double dayToMaturity;
    int moistureLevel;
    double variance;

    String dCrop, dVariance, moistureString, stringMature;
    String[] cornvariety, soyvariety, cornmaturity, soyvariance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_data);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        File file = new File(this.getBaseContext().getFilesDir(), "photo.png");

        cornvariety = getResources().getStringArray(R.array.cVariety_array);
        soyvariety = getResources().getStringArray(R.array.sbVariety_array);
        cornmaturity = getResources().getStringArray(R.array.corn_maturity);
        soyvariance = getResources().getStringArray(R.array.soybeanMaturity);




        Log.i("asdad", cornvariety[30]);
        Log.i("asdad", cornmaturity[30]);

        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

        ImageView myImage = (ImageView) findViewById(R.id.CropImage);

        myImage.setImageBitmap(myBitmap);

        soilTemp = findViewById(R.id.soilTempDisplay);
        moisture = findViewById(R.id.moistureDisplay);
        days = findViewById(R.id.displayTimeLeft);

        DatabaseReference newDataRef = database.getReference("User Update Data");
        DatabaseReference startData = database.getReference("User");

        startData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotTwo) {
                refreshTwo(snapshotTwo.getChildren());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Data Fetching", error.getMessage());
            }
        });

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
    private void refresh(Iterable<DataSnapshot> userUpdateData) {

        for (DataSnapshot user : userUpdateData) {
            if (user.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                NewData newUser = user.getValue(NewData.class);


                moistureString = newUser.getMoisture();

                soilTemp.setText(newUser.getSoilTemp() + "F");
                moisture.setText(newUser.getMoisture() + "%");

            }
        }
    }
        private void refreshTwo(Iterable<DataSnapshot> startingData)
        {
            for (DataSnapshot starting : startingData) {
                if (starting.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    Log.i("test", "refresh: CHECKING FOR GETTING STARTER !!!!!!");
                    User starter = starting.getValue(User.class);

                    dCrop = starter.getCrop();
                    dVariance = starter.getVariety();
                    Log.i("VARIANCE", dVariance);
                    if (moistureString == null && dCrop.equals("Corn")) {
                        Log.i("test", "refresh: CHECKING FOR EMPTY MOISTURE !!!!!!");
                        int i = findStringCorn(dVariance);
                        Log.i("findingI", "refreshTwo: " + i);
                        dayToMaturity = Integer.parseInt(cornmaturity[i]);
                        days.setText(dayToMaturity + " Days");
                    } else if (moistureString == null && dCrop.equals("Soybean")) {
                        int i = findStringSoy(dVariance);
                        dayToMaturity = Integer.parseInt(soyvariance[i]);
                        days.setText(dayToMaturity + " Days");
                    } else {


                        if (dCrop.equals("Corn")) {
                            int i = findStringCorn(dVariance);
                            double initial = Integer.parseInt(cornmaturity[i]);
                            dayToMaturity = Integer.parseInt(cornmaturity[i]);
                            if (moistureLevel == 16) {
                                dayToMaturity = 0;

                            } else if (moistureLevel > 16) {
                                moistureLevel -= 16;
                                for (int k = moistureLevel; k < initial; k++) {
                                    dayToMaturity -= moistureLevel * (initial / (initial - 16));

                                }
                            }
                            days.setText(dayToMaturity + " Days");
                        } else {
                            int i = findStringSoy(dVariance);
                            dayToMaturity = 5 * (Integer.parseInt(soyvariance[i]));
                            dayToMaturity += 45;
                            if (moistureLevel == 13) {
                                dayToMaturity = 0;
                            } else if (moistureLevel > 13) {
                                moistureLevel -= 87;
                                dayToMaturity += ((double) moistureLevel) * 0.5;
                            }
                        }
                        days.setText(dayToMaturity + " Days");
                    }
                }
            }
        }

        public int findStringCorn(String input)
        {
            Log.i("input", input);
            for(int i = 0; i < cornvariety.length; i++)
            {
                Log.i("compare", cornvariety[i]);

                if(input.equals(cornvariety[i]))
                {
                    return i;
                }




            }
                return 0;
        }
    public int findStringSoy(String input)
    {
        for(int i = 0; i < soyvariety.length; i++)
        {
            if(input.equals(soyvariety[i]));
            {
                return i;
            }

        }
        return 0;
    }
}
