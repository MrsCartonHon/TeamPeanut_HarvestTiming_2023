package com.example.teampeanut_harvesttiming_2023;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User
{

    public String email;
    public String name;
    private DatabaseReference mDatabase;
// ...


    public User()
    {

    }
    public User(String name, String email)
    {
        this.email = email;
        this.name = name;
    }

}
