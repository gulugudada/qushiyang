package com.hnucm.qushiyang.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class DishMassage extends AppCompatActivity {
    ImageView imageView33;
    TextView textView39;
    ImageView imageView35;
    TextView textView41;
    TextView textView43;
    TextView textView45;
    ImageView imageView32;
    int flag1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_massage);
        Intent intent = this.getIntent();
        String dishname = intent.getStringExtra("dishname");
        textView39 = findViewById(R.id.textView39);
        textView39.setText(dishname);
        imageView35 = findViewById(R.id.imageView35);
        textView45 = findViewById(R.id.textView45);
        imageView32 = findViewById(R.id.imageView32);
        findViewById(R.id.gif3).setBackgroundResource(WeatherInformation.background);
        imageView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageView33 = findViewById(R.id.imageView33);
        textView41 = findViewById(R.id.textView41);
        textView43 = findViewById(R.id.textView43);
        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findDishByDishname");//访问的请求地址
        requestParams.addQueryStringParameter("dishname", dishname);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    String stock = jsonObject1.getString("stock");
                    String method = jsonObject1.getString("method");
                    String effect = jsonObject1.getString("effect");
                    textView45.setText(stock);
                    textView41.setText(method);
                    textView43.setText(effect);
                    Glide.with(DishMassage.this).load("http://dvwbxngn.dnat.tech/"+ jsonObject1.getString("image")).into(imageView33);
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
        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String username =  sharedPreferences.getString("username","用户名");
        RequestParams requestParams1 = new RequestParams("http://dvwbxngn.dnat.tech/isCollect");//访问的请求地址
        requestParams1.addQueryStringParameter("username",username);
        requestParams1.addQueryStringParameter("dishname",dishname);
        x.http().post(requestParams1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("aaa",result);
                if(result.equals("true")){
                    flag1 = 1;
                    imageView35.setSelected(true);
                }
                else {
                    imageView35.setSelected(false);
                    flag1 = 0;
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
        imageView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1 == 0){
                    imageView35.setSelected(true);
                    flag1 = 1;
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                    String username =  sharedPreferences.getString("username","用户名");
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/addCollect");//访问的请求地址
                    requestParams.addQueryStringParameter("username",username);
                    requestParams.addQueryStringParameter("dishname",dishname);
                    requestParams.addQueryStringParameter("type","dish");
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject1 = new JSONObject(result);
                                String msg = jsonObject1.getString("msg");
                                new AlertDialog.Builder(
                                        DishMassage.this)
                                        .setTitle("提示")
                                        .setMessage(msg)
                                        .setPositiveButton("确定", null).show();
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
                else {
                    imageView35.setSelected(false);
                    flag1 = 0;
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                    String username =  sharedPreferences.getString("username","用户名");
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/deleteCollect");//访问的请求地址
                    requestParams.addQueryStringParameter("username",username);
                    requestParams.addQueryStringParameter("dishname",dishname);
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject1 = new JSONObject(result);
                                String msg = jsonObject1.getString("msg");
                                new AlertDialog.Builder(
                                        DishMassage.this)
                                        .setTitle("提示")
                                        .setMessage(msg)
                                        .setPositiveButton("确定", null).show();
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
            }
        });

    }
}