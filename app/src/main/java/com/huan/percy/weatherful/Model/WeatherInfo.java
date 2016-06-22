package com.huan.percy.weatherful.Model;

import java.io.Serializable;

/**
 * Created by Percy on 2016/6/22.
 */
public class WeatherInfo implements Serializable {
    // 天气现象文字，例如“多云”
    private String desc;
    // 天气现象代码，例如“4”
    private String code;
    // 温度，单位为c摄氏度或f华氏度
    private int temperature;

    private int imageId;

    private String date;

    private String loaction;

    public String getLoaction() {
        return loaction;
    }

    public void setLoaction(String loaction) {
        this.loaction = loaction;
    }



    public WeatherInfo(String desc, String code, int temperature, int imageId) {
        this.desc = desc;
        this.code = code;
        this.temperature = temperature;
        this.imageId = imageId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



}
