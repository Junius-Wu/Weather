package com.jun.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textView_refreshHint)
    TextView textViewRefreshHint;
    @Bind(R.id.textview_city)
    TextView textviewCity;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.imageview_weather)
    ImageView imageviewWeather;
    @Bind(R.id.textview_low_temperature)
    TextView textviewLowTemperature;
    @Bind(R.id.textview_high_temperature)
    TextView textviewHighTemperature;
    @Bind(R.id.textview_now_temperature)
    TextView textviewNowTemperature;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.textview_weather)
    TextView textviewWeather;
    @Bind(R.id.recyclerView_future_weather)
    RecyclerView recyclerViewFutureWeather;

    private SwipeRefreshLayout swipeRefreshLayout;
    RelativeLayout mainLayout;
    WeatherData weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);

        //一开始就先刷新一次
        textViewRefreshHint.setText("正在刷新");
        new UpdataTask().execute();//异步执行 数据的更新

        /////////////////////////////////下拉刷新
        textViewRefreshHint = (TextView) findViewById(R.id.textView_refreshHint);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        //设置刷新时动画的颜色，可以设置4个 没什么卵用
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {//下拉刷新

            @Override
            public void onRefresh() {
                textViewRefreshHint.setText("正在刷新");
                new UpdataTask().execute();//异步执行 数据的更新

            }
        });
    }

    public class UpdataTask extends AsyncTask<Void, Integer, Boolean> {//异步更新数据任务


        @Override
        protected Boolean doInBackground(Void... params) {//新线程中

            try {
                weatherData = GetWeatherData.getData("%E6%9D%AD%E5%B7%9E");//杭州的UTF-8码
                System.out.println("doInBackground::" + weatherData);
                if (weatherData != null) {//没有网络时会返回空
                    return true;
                } else {
                    return false;
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {//执行完后 可以操作ui
            super.onPostExecute(aBoolean);

            if (aBoolean) {//这里的aBoolean就是doInBackground 返回的值
                textViewRefreshHint.setText("刷新成功");
                refreshUI(weatherData);//刷新UI数据
                swipeRefreshLayout.setRefreshing(false);
            } else {
                textViewRefreshHint.setText("刷新失败 请检查网络");
                swipeRefreshLayout.setRefreshing(false);

            }
        }

        private void refreshUI(WeatherData weatherData) {//刷新UI数据
            textviewWeather.setText(weatherData.getWeather());
            textviewLowTemperature.setText(weatherData.getLowC());
            textviewNowTemperature.setText(weatherData.getNowC());
            textviewHighTemperature.setText(weatherData.getHighC());
            textView.setText(weatherData.getTime() + "发布");//这个是发布时间的textview

            setRecyclerViewFutureWeatherData(weatherData);

        }

        private void setRecyclerViewFutureWeatherData(WeatherData weatherData) {//设置未来天气数据
            recyclerViewFutureWeather.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false));
            recyclerViewFutureWeather.setAdapter(new FutureWeatherAdapter(weatherData));
        }


    }

}
