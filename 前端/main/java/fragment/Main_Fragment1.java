package com.hnucm.qushiyang.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.homepage.SeasonFragment;
import com.hnucm.qushiyang.homepage.TiZhiFragment;
import com.hnucm.qushiyang.homepage.WeatherActivity;
import com.hnucm.qushiyang.utils.AddressInformation;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import pl.droidsonroids.gif.GifImageView;


public class Main_Fragment1 extends Fragment {
    public Activity mActivity;
    ImageView weather;
    GifImageView gifImageView;
    SeasonFragment seasonFragment = new SeasonFragment();
    TiZhiFragment tiZhiFragment = new TiZhiFragment();
//    Fragment_tizhituijian fragment_tizhituijian = new Fragment_tizhituijian();
//    Fragment_tianqi fragment_tianqi = new Fragment_tianqi();
//    Fragment_tianqituijian fragment_tianqituijian = new Fragment_tianqituijian();
//    ConstraintLayout constraintLayout15;
//    ConstraintLayout constraintLayout16;
//    ConstraintLayout constraintLayout17;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_1, container, false);
//        constraintLayout15 = view.findViewById(R.id.constraintLayout15);
//        constraintLayout16 = view.findViewById(R.id.constraintLayout16);
//        constraintLayout17 = view.findViewById(R.id.constraintLayout17);
//        getFragmentManager().beginTransaction().replace(R.id.fragment2, fragment_tizhituijian).commit();
//
//        constraintLayout15.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.fragment2, fragment_tizhituijian).commit();
//            }
//        });
//
//        constraintLayout16.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.fragment2, fragment_tianqi).commit();
//            }
//        });
//
//        constraintLayout17.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.fragment2, fragment_tianqituijian).commit();
//            }
//        });
        TextView date = view.findViewById(R.id.date);
        TextView time = view.findViewById(R.id.time);
        TextView address = view.findViewById(R.id.address);
        weather = view.findViewById(R.id.weather);
        TextView wendu = view.findViewById(R.id.wendu);
        TextView fengxiang = view.findViewById(R.id.fengxiang);
        TextView fengli = view.findViewById(R.id.fengli);
        TextView tips = view.findViewById(R.id.tips);
        gifImageView = mActivity.findViewById(R.id.gif);
        TextView gengduotianqi = view.findViewById(R.id.gengduotianqi);
        TextView seasontuijian = view.findViewById(R.id.textView20);
        TextView tizhisontuijian = view.findViewById(R.id.textView21);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy???MM???dd??? HH:mm:ss");
        Calendar c = Calendar.getInstance();
//        ???????????????????????????
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Date curDate = new Date(System.currentTimeMillis());
                    c.setTime(curDate);
                    mActivity.runOnUiThread(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            date.setText(formatter.format(curDate).split(" ")[0]+getWeek(c));
                            time.setText(formatter.format(curDate).split(" ")[1]);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
//        ?????????????????????????????????
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(AddressInformation.city != null){
                        String address1 = AddressInformation.province+AddressInformation.city+AddressInformation.district+AddressInformation.street;
                        mActivity.runOnUiThread(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                address.setText(address1);
                            }
                        });
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }).start();
//        ?????????????????????????????????
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(AddressInformation.city != null){
                        RequestParams requestParams = new RequestParams("https://api.vvhan.com/api/weather?city=" + AddressInformation.city);
                        x.http().post(requestParams, new Callback.CommonCallback<String>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onSuccess(String result) {
//                                {
//                                    "success":true,
//                                     "city":"?????????",
//                                     "info":
//                                            {"date":"12????????????",
//                                            "type":"??????",
//                                            "high":"?????? 10???",
//                                            "low":"?????? 7???",
//                                            "fengxiang":"??????",
//                                            "fengli":"3???",
//                                            "tip":"???????????????????????????????????????????????????????????????????????????????????????"
//                                    }
//                                }
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    JSONObject jsonObject1 = new JSONObject(jsonObject.getJSONObject("info").toString());
                                    WeatherInformation.type = jsonObject1.getString("type");
                                    WeatherInformation.high = jsonObject1.getString("high").split(" ")[1];
                                    WeatherInformation.low = jsonObject1.getString("low").split(" ")[1];
                                    WeatherInformation.fengxiang = jsonObject1.getString("fengxiang");
                                    WeatherInformation.fengli = jsonObject1.getString("fengli");
                                    WeatherInformation.tips = jsonObject1.getString("tip");
                                    getWeatherGIF(WeatherInformation.type);
                                    wendu.setText(WeatherInformation.high + "/" + WeatherInformation.low);
                                    fengxiang.setText(WeatherInformation.fengxiang);
                                    fengli.setText(WeatherInformation.fengli);
                                    tips.setText(WeatherInformation.tips);
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
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }).start();
        gengduotianqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, WeatherActivity.class);
                startActivity(intent);
            }
        });
        getChildFragmentManager().beginTransaction().replace(R.id.con,seasonFragment).commit();
        seasontuijian.setTextColor(Color.parseColor("#FFEB3B"));
        seasontuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.con,seasonFragment).commit();
                seasontuijian.setTextColor(Color.parseColor("#FFEB3B"));
                tizhisontuijian.setTextColor(Color.parseColor("#000000"));
            }
        });
        tizhisontuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.con,tiZhiFragment).commit();
                seasontuijian.setTextColor(Color.parseColor("#000000"));
                tizhisontuijian.setTextColor(Color.parseColor("#FFEB3B"));
            }
        });
        return view;
    }

    public String getWeek(Calendar calendar){
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return "?????????";
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            return "?????????";
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            return "?????????";
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            return "?????????";
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            return "?????????";
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            return "?????????";
        }
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return "?????????";
        }
        return null;
    }

    public void getWeatherGIF(String weather1){
        if(weather1.contains("???")){
            weather.setImageResource(R.drawable.qing);
            gifImageView.setBackgroundResource(R.drawable.sunny);
            WeatherInformation.background = R.drawable.sunny;
        }
        else if(weather1.contains("???")){
            weather.setImageResource(R.drawable.xue);
            gifImageView.setBackgroundResource(R.drawable.snow);
            WeatherInformation.background = R.drawable.snow;
        }
        else if(weather1.contains("???")){
            weather.setImageResource(R.drawable.yu);
            gifImageView.setBackgroundResource(R.drawable.rain);
            WeatherInformation.background = R.drawable.rain;
        }
        else if(weather1.contains("??????")){
            weather.setImageResource(R.drawable.duoyun);
            gifImageView.setBackgroundResource(R.drawable.cloudy);
            WeatherInformation.background = R.drawable.cloudy;
        }
        else {
            if(weather1.contains("???")){
                weather.setImageResource(R.drawable.yin);
            }
            else if(weather1.contains("???")){
                weather.setImageResource(R.drawable.mai);
            }
            gifImageView.setBackgroundResource(R.drawable.defaul);
            WeatherInformation.background = R.drawable.defaul;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }
}