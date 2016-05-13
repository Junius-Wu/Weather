package com.jun.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jun on 16/5/13.
 */
public class FutureWeatherAdapter extends RecyclerView.Adapter {

    private WeatherData weatherData;

    public FutureWeatherAdapter(WeatherData weatherData) {
        this.weatherData = weatherData;
    }


    class ViewHolder extends RecyclerView.ViewHolder {//内部类 viewholder

        private View root;
        private TextView textView_weather;
        private TextView textView_week;
        private TextView textView_wind;
        private TextView textView_temperature;
        private TextView textView_date;

        public ViewHolder(View root) {//构造函数 获得cell
            super(root);
            textView_weather = (TextView) root.findViewById(R.id.textView_weather);
            textView_week = (TextView) root.findViewById(R.id.textView_week);
            textView_wind = (TextView) root.findViewById(R.id.textView_wind);
            textView_temperature = (TextView) root.findViewById(R.id.textView_temperature);
            textView_date = (TextView) root.findViewById(R.id.textView_date);
        }

        public TextView getTextView_weather() {
            return textView_weather;
        }

        public TextView getTextView_week() {
            return textView_week;
        }

        public TextView getTextView_wind() {
            return textView_wind;
        }

        public TextView getTextView_temperature() {
            return textView_temperature;
        }

        public TextView getTextView_date() {
            return textView_date;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//创建
        //inflate listCell
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {//赋值
        ViewHolder viewHolder = (ViewHolder) holder;
        WeatherData weatherDataAtPosition = weatherData.getFutureWeatherDatas().get(position);

        viewHolder.getTextView_date().setText(weatherDataAtPosition.getDate());
        viewHolder.getTextView_temperature().setText(weatherDataAtPosition.getTemperature());
        viewHolder.getTextView_wind().setText(weatherDataAtPosition.getWind());
        viewHolder.getTextView_weather().setText(weatherDataAtPosition.getWeather());
        viewHolder.getTextView_week().setText(weatherDataAtPosition.getWeek());
    }

    @Override
    public int getItemCount() {//个数
        System.out.println("-----" + weatherData.getFutureWeatherDatas().size());
        return weatherData.getFutureWeatherDatas().size();
    }
}
