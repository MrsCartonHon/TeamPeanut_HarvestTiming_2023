package com.example.teampeanut_harvesttiming_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.teampeanut_harvesttiming_2023.databinding.ActivityWeatherBinding;
import com.example.teampeanut_harvesttiming_2023.databinding.ActivityWeatherBinding;
import com.example.teampeanut_harvesttiming_2023.databinding.ActivityWeatherBinding;
import com.example.teampeanut_harvesttiming_2023.model.MyWeather;

public class WeatherActivity extends AppCompatActivity implements MyWeatherTaskListener
{
    private ActivityWeatherBinding binding;

    //Web URL of the JSON file
    private String mApiKey = "be9ed750059f773fe3d97f7f2c0ebf79";
    private String mCity = "Bettendorf";
    private String mCountry = "United States";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //http://api.openweathermap.org/data/2.5/weather?q=city,country&APPID={your api key};
        String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=" + mCity + "," + mCountry + "&APPID=" + mApiKey;
        new MyWeatherTask(this).execute(weatherURL);
    }

    @Override
    public void onMyWeatherTaskPreExecute()
    {
        binding.myLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMyWeatherTaskPostExecute(MyWeather myWeather)
    {
        if (myWeather != null)
        {
            binding.cityTextView.setText(mCity);
            binding.countryTextView.setText(mCountry);

            binding.weatherConditionTextView.setText(myWeather.getWeatherCondition());
            binding.weatherDescriptionTextView.setText(myWeather.getWeatherDescription());

            int temp = Math.round(myWeather.getTemperature() - 273.15f);
            String tempStr = String.valueOf(temp);
            binding.temperatureTextView.setText(tempStr);

            String imgUrl = "https://openweathermap.org/img/wn/" + myWeather.getWeatherIconStr() + "@2x.png";

            Glide.with(WeatherActivity.this)
                    .asBitmap()
                    .load(imgUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(binding.weatherIconImageView);
        }
        binding.myLoadingLayout.setVisibility(View.GONE);
    }
}