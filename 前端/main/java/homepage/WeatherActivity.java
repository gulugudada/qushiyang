package com.hnucm.qushiyang.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.entity.Weather;
import com.hnucm.qushiyang.utils.AddressInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class WeatherActivity extends AppCompatActivity {
    List<Weather> weatherList = new ArrayList<Weather>();
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    GifImageView gifImageView;
    ImageView weather;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        RecyclerView recyclerView = findViewById(R.id.rec);
        gifImageView = findViewById(R.id.gif1);
        TextView address = findViewById(R.id.address1);
        weather = findViewById(R.id.weather2);
        TextView wendu = findViewById(R.id.wendu2);
        TextView fengxiang = findViewById(R.id.fengxiang1);
        TextView fengli = findViewById(R.id.fengli1);
        TextView tips = findViewById(R.id.tips1);
        address.setText(AddressInformation.province+AddressInformation.city+AddressInformation.district);
        RequestParams requestParams = new RequestParams("https://api.vvhan.com/api/weather?city=" + AddressInformation.city + "&type=week");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                {
//                    "data":
//                        {
//                            "yesterday":
//                                {
//                                    "date":"22????????????",
//                                    "high":"?????? 19???",
//                                    "fx":"??????",
//                                    "low":"?????? 5???",
//                                    "fl":"1???",
//                                    "type":"???"
//                                },
//                                "city":"??????",
//                                "forecast":
//                                    [
//                                        {
//                                            "date":"23????????????",
//                                            "high":"?????? 18???",
//                                            "fengli":"1???",
//                                            "low":"?????? 6???",
//                                            "fengxiang":"?????????",
//                                            "type":"???"
//                                        },
//                                        {
//                                            "date":"24????????????",
//                                            "high":"?????? 11???",
//                                            "fengli":"3???",
//                                            "low":"?????? 3???",
//                                            "fengxiang":"??????",
//                                            "type":"???"
//                                        },
//                                        {
//                                            "date":"25????????????",
//                                            "high":"?????? 5???",
//                                            "fengli":"4???",
//                                            "low":"?????? 0???",
//                                            "fengxiang":"??????",
//                                            "type":"???"},
//                                        {
//                                            "date":"26????????????",
//                                            "high":"?????? 3???",
//                                            "fengli":"3???",
//                                            "low":"?????? 0???",
//                                            "fengxiang":"??????",
//                                            "type":"??????"},
//                                        {
//                                            "date":"27????????????",
//                                            "high":"?????? 5???",
//                                            "fengli":"1???",
//                                            "low":"?????? -1???",
//                                            "fengxiang":"?????????",
//                                            "type":"??????"
//                                        }
//                                    ],
//                                    "ganmao":"?????????????????????????????????????????????????????????????????????",
//                                    "wendu":"11"
//                        },
//                        "status":1000,
//                        "desc":"OK"
//                }
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("yesterday");
                    Weather weather1 = new Weather();
                    weather1.setDate(jsonObject2.getString("date"));
                    weather1.setType(jsonObject2.getString("type"));
                    weather1.setWendu(jsonObject2.getString("high").split(" ")[1] + '/' + jsonObject2.getString("low").split(" ")[1]);
                    weather1.setFengxiang(jsonObject2.getString("fx"));
                    weather1.setFengli(jsonObject2.getString("fl"));
                    weatherList.add(weather1);
                    Weather weather2 = new Weather();
                    JSONArray jsonArray = jsonObject1.getJSONArray("forecast");
                    weather2.setDate(jsonArray.getJSONObject(0).getString("date"));
                    weather2.setType(jsonArray.getJSONObject(0).getString("type"));
                    weather2.setWendu(jsonArray.getJSONObject(0).getString("high").split(" ")[1] + '/' + jsonArray.getJSONObject(0).getString("low").split(" ")[1]);
                    weather2.setFengxiang(jsonArray.getJSONObject(0).getString("fengxiang"));
                    weather2.setFengli(jsonArray.getJSONObject(0).getString("fengli"));
                    getWeatherGIF(weather2.getType());
                    wendu.setText(weather2.getWendu());
                    fengxiang.setText(weather2.getFengxiang());
                    fengli.setText(weather2.getFengli());
                    weatherList.add(weather2);
                    address.setText(AddressInformation.province+AddressInformation.city+AddressInformation.district+AddressInformation.street);
                    tips.setText(jsonObject1.getString("ganmao"));
                    for(int i = 1;i<jsonArray.length();i++){
                        Weather weather3 = new Weather();
                        weather3.setDate(jsonArray.getJSONObject(i).getString("date"));
                        weather3.setType(jsonArray.getJSONObject(i).getString("type"));
                        weather3.setWendu(jsonArray.getJSONObject(i).getString("high").split(" ")[1] + '/' + jsonArray.getJSONObject(i).getString("low").split(" ")[1]);
                        weather3.setFengxiang(jsonArray.getJSONObject(i).getString("fengxiang"));
                        weather3.setFengli(jsonArray.getJSONObject(i).getString("fengli"));
                        weatherList.add(weather3);
                    }
                    myAdapter = new MyAdapter();
                    LinearLayoutManager layoutManager= new LinearLayoutManager(WeatherActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(myAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(WeatherActivity.this).inflate(R.layout.item_list,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.riqi.setText(weatherList.get(position).getDate());
            holder.zuojinhou.setText(getZuoJinHou(position));
            holder.image.setImageResource(getWeatherIcon(weatherList.get(position).getType()));
            holder.wendu.setText(weatherList.get(position).getWendu());
            holder.fengxiang.setText(weatherList.get(position).getFengxiang());
            holder.fengli.setText(weatherList.get(position).getFengli());
        }

        @Override
        public int getItemCount() {
            return weatherList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView riqi;
        TextView zuojinhou;
        ImageView image;
        TextView wendu;
        TextView fengxiang;
        TextView fengli;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            riqi = itemView.findViewById(R.id.riqi);
            zuojinhou = itemView.findViewById(R.id.textView);
            image = itemView.findViewById(R.id.imageView);
            wendu = itemView.findViewById(R.id.textView2);
            fengxiang = itemView.findViewById(R.id.textView11);
            fengli = itemView.findViewById(R.id.textView17);
        }
    }

    public void getWeatherGIF(String weather1){
        if(weather1.contains("???")){
            weather.setImageResource(R.drawable.qing);
            gifImageView.setBackgroundResource(R.drawable.sunny);
        }
        else if(weather1.contains("???")){
            weather.setImageResource(R.drawable.xue);
            gifImageView.setBackgroundResource(R.drawable.snow);
        }
        else if(weather1.contains("???")){
            weather.setImageResource(R.drawable.yu);
            gifImageView.setBackgroundResource(R.drawable.rain);
        }
        else if(weather1.contains("??????")){
            weather.setImageResource(R.drawable.duoyun);
            gifImageView.setBackgroundResource(R.drawable.cloudy);
        }
        else {
            if(weather1.contains("???")){
                weather.setImageResource(R.drawable.mai);
            }
            else if(weather1.contains("???")){
                weather.setImageResource(R.drawable.yin);
            }
            gifImageView.setBackgroundResource(R.drawable.defaul);
        }
    }

    public int getWeatherIcon(String weather1){
        if(weather1.contains("???")){
            return R.drawable.qing;
        }
        else if(weather1.contains("???")){
            return R.drawable.xue;
        }
        else if(weather1.contains("???")){
            return R.drawable.yu;
        }
        else if(weather1.contains("??????")){
            return R.drawable.duoyun;
        }
        else if(weather1.contains("???")){
            return R.drawable.yin;
        }
        else if(weather1.contains("???")){
            return R.drawable.mai;
        }
        else {
            return 0;
        }
    }

    public String getZuoJinHou(int i){
        switch (i){
            case 0:return "??????";
            case 1:return "??????";
            case 2:return "??????";
            case 3:return "?????????";
            case 4:return "????????????";
            case 5:return "???????????????";
        }
        return "???????????????";
    }
}