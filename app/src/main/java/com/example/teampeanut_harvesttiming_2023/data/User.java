package com.example.teampeanut_harvesttiming_2023.data;

public class User
{
    private String crop, variety, soil, fertilizer;

    public User()
    {}

    public User(String crop, String variety) {
        this.crop = crop;
        this.variety = variety;

    }

    public String getCrop() {
        return crop;
    }

    public String getVariety() {
        return variety;
    }


}
