package com.example.teampeanut_harvesttiming_2023.data;

public class User
{
    private String crop, variety, soil, fertilizer;

    public User(String crop, String variety, String soil, String fertilizer) {
        this.crop = crop;
        this.variety = variety;
        this.soil = soil;
        this.fertilizer = fertilizer;
    }

    public String getCrop() {
        return crop;
    }

    public String getVariety() {
        return variety;
    }

    public String getSoil() {
        return soil;
    }

    public String getFertilizer() {
        return fertilizer;
    }

}
