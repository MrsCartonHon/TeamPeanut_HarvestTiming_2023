package com.example.teampeanut_harvesttiming_2023;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Storage
{


    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
public void sendToDatabase()
{
    myRef.setValue("Hello, World!");
}

}
