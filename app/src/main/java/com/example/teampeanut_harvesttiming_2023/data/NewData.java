package com.example.teampeanut_harvesttiming_2023.data;

public class NewData
{

    private String moisture, soilTemp;


    public NewData()
    {

    }
    public NewData(String moisture, String soilTemp) {
        this.moisture = moisture;
        this.soilTemp = soilTemp;
    }

    public String getMoisture() {
        return moisture;
    }

    public String getSoilTemp() {
        return soilTemp;
    }
}
