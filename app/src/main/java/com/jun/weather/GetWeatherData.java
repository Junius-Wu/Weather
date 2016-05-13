package com.jun.weather;

/**
 * Created by jun on 16/5/11.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jun on 16/5/11.
 */

public class GetWeatherData {

    /**
     * 根据城市名获取
     *
     * @param cityName
     * @return
     */
    public static String excute(String cityName) {
        String url =//此处以返回json格式数据示例,所以format=2,以根据城市名称为例,cityName传入中文
                 "http://v.juhe.cn/weather/index?cityname=" + cityName +
                        "&key=22ce1a2fa4b38f916ad77e0f309450b9" +
                        "&format=2" +
                        "&dtype=json";
        
        return  PureNetUtil.get(url);//通过工具类获取返回数据
    }

    /**
     * 获取数据 返回WeatherData model
     */
    public static WeatherData getData(String city) throws JSONException {
        //设置weatherData 并返回
        String all = excute(city);
        if (all != null) {
            JSONObject obj = new JSONObject(all);
            /*获取返回状态码*/
            all = obj.getString("resultcode");
            /*如果状态码是200说明返回数据成功*/
            if (all != null && all.equals("200")) {
                // 创建要返回的数据
                WeatherData weatherData = new WeatherData();


                //一。获得result节点
                String result = obj.getString("result");
                //此时result中数据有多个key,可以对其key进行遍历,得到对个属性
                JSONObject resultObj = new JSONObject(result);



                //1.当前天气对应的key是sk
                JSONObject skObj = new JSONObject(resultObj.getString("sk"));
                //1.1当前温度
                String nowC = skObj.getString("temp");
                weatherData.setNowC(nowC);
                //1.2发布时间
                String time = skObj.getString("time");
                weatherData.setTime(time);
                //1.3湿度
                String humidity = skObj.getString("humidity");
                weatherData.setHumidity(humidity);
                //1.4风向
                String wind_direction = skObj.getString("wind_direction");
                weatherData.setWind_direction(wind_direction);
                //1.5风力
                String wind_strength = skObj.getString("wind_strength");
                weatherData.setWind_strength(wind_strength);

                //2.今日温度对应的key是today
                JSONObject todayObj = new JSONObject(resultObj.getString("today"));
                //2.1今日最高与最低温度
                String temperature = todayObj.getString("temperature");
                String[] temp = temperature.split("~");
                weatherData.setLowC(temp[0]);
                weatherData.setHighC(temp[1]);
                //2.2天气 ：晴转多云？
                String weather = todayObj.getString("weather");
                weatherData.setWeather(weather);
                //2.3星期几
                String week = todayObj.getString("week");
                weatherData.setWeek(week);
                //2.4穿衣建议
                weatherData.setDressing_advice(todayObj.getString("dressing_advice"));

                //3.未来 7天的天气
                JSONArray jsonArr= new JSONArray(resultObj.getString("future"));
                for (int i = 0; i < jsonArr.length(); i++) {
                    WeatherData futureData = new WeatherData();
                    futureData.setTemperature(jsonArr.getJSONObject(i).getString("temperature"));
                    futureData.setWeather(jsonArr.getJSONObject(i).getString("weather"));
                    futureData.setWeek(jsonArr.getJSONObject(i).getString("week"));
                    futureData.setWind(jsonArr.getJSONObject(i).getString("wind"));
                    futureData.setDate(jsonArr.getJSONObject(i).getString("date"));
                    //添加到futureWeatherDatas (ArrayList 中)
                    weatherData.getFutureWeatherDatas().add(futureData);
                }



                return weatherData;
            }
        }
        return null;
    }



}


