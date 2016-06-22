package com.huan.percy.weatherful.Until;

import com.huan.percy.weatherful.Model.WeatherInfo;
import com.huan.percy.weatherful.R;
import com.thinkpage.lib.api.TPWeatherNow;

import java.util.Calendar;

/**
 * Created by Percy on 2016/6/22.
 */
public class WeatherInfoHandler {



    public static void saveWeatherNowInfo(TPWeatherNow tpWeatherNow, WeatherInfo weatherInfo){
        weatherInfo.setCode(tpWeatherNow.code);
        weatherInfo.setDesc(tpWeatherNow.text);
        weatherInfo.setTemperature(tpWeatherNow.temperature);
        Calendar c = Calendar.getInstance();
        String date = c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH);
        weatherInfo.setDate(date);

        weatherInfo.setImageId(R.drawable.ic_launcher);
        weatherInfo.setLoaction("大连");
    }

}
