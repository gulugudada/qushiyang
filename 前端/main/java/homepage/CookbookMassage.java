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
import com.hnucm.qushiyang.login.LoginActivity;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class CookbookMassage extends AppCompatActivity {
    ImageView imageView26;
    TextView textView35;
    TextView textView37;
    ImageView imageView28;
    ImageView imageView31;
    String season;
    int flag1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook_massage);
        imageView26 = findViewById(R.id.imageView26);
        imageView28 = findViewById(R.id.imageView28);
        findViewById(R.id.gif2).setBackgroundResource(WeatherInformation.background);
        Intent intent = this.getIntent();
        String cookbook = intent.getStringExtra("cookbook");
        imageView26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView35 = findViewById(R.id.textView35);
        textView37 = findViewById(R.id.textView37);
        textView35.setText(cookbook);
        imageView31 = findViewById(R.id.imageView31);

        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findSeasonTuiJianByCookbook");//访问的请求地址
        requestParams.addQueryStringParameter("cookbook", cookbook);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    String method = jsonObject1.getString("method");
                    season = jsonObject1.getString("season");
                    textView37.setText(method);
                    Glide.with(CookbookMassage.this).load("http://dvwbxngn.dnat.tech/"+ jsonObject1.getString("image")).into(imageView28);

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
        requestParams1.addQueryStringParameter("dishname",cookbook);
        x.http().post(requestParams1, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("aaa",result);
                        if(result.equals("true")){
                            flag1 = 1;
                            imageView31.setSelected(true);
                        }
                        else {
                            imageView31.setSelected(false);
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

        imageView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1 == 0){
                    imageView31.setSelected(true);
                    flag1 = 1;
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                    String username =  sharedPreferences.getString("username","用户名");
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/addCollect");//访问的请求地址
                    requestParams.addQueryStringParameter("username",username);
                    requestParams.addQueryStringParameter("dishname",cookbook);
                    requestParams.addQueryStringParameter("type","seasontuijian");
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject1 = new JSONObject(result);
                                String msg = jsonObject1.getString("msg");
                                new AlertDialog.Builder(
                                        CookbookMassage.this)
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
                    imageView31.setSelected(false);
                    flag1 = 0;
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                    String username =  sharedPreferences.getString("username","用户名");
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/deleteCollect");//访问的请求地址
                    requestParams.addQueryStringParameter("username",username);
                    requestParams.addQueryStringParameter("dishname",cookbook);
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject1 = new JSONObject(result);
                                String msg = jsonObject1.getString("msg");
                                new AlertDialog.Builder(
                                        CookbookMassage.this)
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