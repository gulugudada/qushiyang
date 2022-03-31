package com.hnucm.qushiyang.druglist;

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
import com.hnucm.qushiyang.homepage.CookbookMassage;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MedicineDetailsActivity extends AppCompatActivity {
    TextView textView13;
    TextView chineseName;
    TextView Alias;
    TextView MedicinalParts;
    TextView OriginDistribution;
    TextView MedicinalProperties;
    TextView EfficacyAndFunction;
    TextView ClinicalApplication;
    TextView UseTaboo;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView38;
    int flag2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);
        textView13 = findViewById(R.id.textView13);
        chineseName = findViewById(R.id.chinesename);
        Alias = findViewById(R.id.Alias);
        MedicinalParts = findViewById(R.id.MedicinalParts);
        OriginDistribution = findViewById(R.id.OriginDistribution);
        MedicinalProperties = findViewById(R.id.MedicinalProperties);
        EfficacyAndFunction = findViewById(R.id.EfficacyAndFunction);
        ClinicalApplication = findViewById(R.id.ClinicalApplication);
        UseTaboo = findViewById(R.id.UseTaboo);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        findViewById(R.id.gif4).setBackgroundResource(WeatherInformation.background);
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = this.getIntent();
        String ChineseName = intent.getStringExtra("ChineseName");
        RequestParams requestParams1 = new RequestParams("http://dvwbxngn.dnat.tech/FindMagess");
        requestParams1.addParameter("ChineseName",ChineseName);
        x.http().post(requestParams1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String chinesename = jsonObject.getString("ChineseName");
                    String alias = jsonObject.getString("Alias");
                    String medicinalparts = jsonObject.getString("MedicinalParts");
                    String origindistribution = jsonObject.getString("OriginDistribution");
                    String medicinalproperties = jsonObject.getString("MedicinalProperties");
                    String efficacyandfunction = jsonObject.getString("EfficacyAndFunction");
                    String clinicalapplication = jsonObject.getString("ClinicalApplication");
                    String usetaboo = jsonObject.getString("UseTaboo");
                    String img = jsonObject.getString("Image");
                    textView13.setText("      "+chinesename);
                    chineseName.setText("      "+chinesename);
                    Alias.setText("      "+alias);
                    MedicinalParts.setText("      "+medicinalparts);
                    OriginDistribution.setText("      "+origindistribution);
                    MedicinalProperties.setText("      "+medicinalproperties);
                    EfficacyAndFunction.setText("      "+efficacyandfunction);
                    ClinicalApplication.setText("      "+clinicalapplication);
                    UseTaboo.setText("      "+usetaboo);
                    Glide.with(getApplicationContext()).load("http://dvwbxngn.dnat.tech/"+img).into(imageView9);
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
        imageView38 = findViewById(R.id.imageView38);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String username =  sharedPreferences.getString("username","用户名");
        RequestParams requestParams2 = new RequestParams("http://dvwbxngn.dnat.tech/isCollect");//访问的请求地址
        requestParams2.addQueryStringParameter("username",username);
        requestParams2.addQueryStringParameter("dishname",ChineseName);
        x.http().post(requestParams2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("aaa",result);
                if(result.equals("true")){
                    flag2 = 1;
                    imageView38.setSelected(true);
                }
                else {
                    imageView38.setSelected(false);
                    flag2 = 0;
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
        imageView38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2 == 0){
                    imageView38.setSelected(true);
                    flag2 = 1;
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                    String username =  sharedPreferences.getString("username","用户名");
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/addCollect");//访问的请求地址
                    requestParams.addQueryStringParameter("username",username);
                    requestParams.addQueryStringParameter("dishname",ChineseName);
                    requestParams.addQueryStringParameter("type","druglist");
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject1 = new JSONObject(result);
                                String msg = jsonObject1.getString("msg");
                                new AlertDialog.Builder(
                                        MedicineDetailsActivity.this)
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

                }else {
                    imageView38.setSelected(false);
                    flag2 = 0;
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                    String username =  sharedPreferences.getString("username","用户名");
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/deleteCollect");//访问的请求地址
                    requestParams.addQueryStringParameter("username",username);
                    requestParams.addQueryStringParameter("dishname",ChineseName);
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject1 = new JSONObject(result);
                                String msg = jsonObject1.getString("msg");
                                new AlertDialog.Builder(
                                        MedicineDetailsActivity.this)
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