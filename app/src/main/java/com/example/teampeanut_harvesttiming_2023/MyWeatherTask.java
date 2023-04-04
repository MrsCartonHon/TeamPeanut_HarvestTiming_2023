package com.example.teampeanut_harvesttiming_2023;

import android.os.AsyncTask;

import com.example.teampeanut_harvesttiming_2023.model.MyWeather;

public class MyWeatherTask extends AsyncTask<String, Void, MyWeather>
{
    private MyWeatherTaskListener mListener;

    MyWeatherTask(MyWeatherTaskListener pListener)
    {
        this.mListener = pListener;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        mListener.onMyWeatherTaskPreExecute();
    }

    @Override
    protected MyWeather doInBackground(String... strings)
    {
        MyWeather myWeather = null;

        //Fetch Weather
        String jsonStr = MyWeatherClient.fetchWeather(strings[0]);

        //Parsing Weather
        if (jsonStr != null)
        {
            myWeather = MyJSONParser.getMyWeather(jsonStr);
        }
        return myWeather;
    }

    @Override
    protected void onPostExecute(MyWeather myWeather)
    {
        super.onPostExecute(myWeather);
        mListener.onMyWeatherTaskPostExecute(myWeather);
    }
}

interface MyWeatherTaskListener
{
    void onMyWeatherTaskPreExecute();
    void onMyWeatherTaskPostExecute(MyWeather myWeather);
}