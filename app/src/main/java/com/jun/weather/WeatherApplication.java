package com.jun.weather;

import android.app.Application;

import com.thinkland.sdk.android.JuheSDKInitializer;

/**
 * Created by jun on 16/5/11.
 */
public class WeatherApplication extends Application {

    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        JuheSDKInitializer.initialize(getApplicationContext());//初始化聚合SDK
    }

}
