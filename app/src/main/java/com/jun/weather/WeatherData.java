package com.jun.weather;

import java.util.ArrayList;

/**
 * Created by jun on 16/5/12.
 */
public class WeatherData {
    String lowC;//最低温度
    String highC;//最高温度
    String nowC;//现在温度
    String weather;//今天天气
    String time;//发布时间
    String humidity;//湿度
    String wind_strength;//风力
    String wind_direction;//风向
    String dressing_advice;//穿衣建议
    String week;//星期几
    String temperature;//0~20C
    String wind;//风力加风向
    String date;//日期

    ArrayList<WeatherData> futureWeatherDatas = new ArrayList<WeatherData>();//未来几天的天气

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDressing_advice() {
        return dressing_advice;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }

    public String getWind_strength() {
        return wind_strength;
    }

    public void setWind_strength(String wind_strength) {
        this.wind_strength = wind_strength;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public ArrayList<WeatherData> getFutureWeatherDatas() {
        return futureWeatherDatas;
    }

    public void setFutureWeatherDatas(ArrayList<WeatherData> futureWeatherDatas) {
        this.futureWeatherDatas = futureWeatherDatas;
    }

    public String getHighC() {
        return highC;
    }

    public void setHighC(String highC) {
        this.highC = highC;
    }

    public String getLowC() {
        return lowC;
    }

    public void setLowC(String lowC) {
        this.lowC = lowC;
    }

    public String getNowC() {
        return nowC;
    }

    public void setNowC(String nowC) {
        this.nowC = nowC;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
