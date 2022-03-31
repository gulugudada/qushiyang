package com.hnucm.qushiyang.Me;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.druglist.MedicineDetailsActivity;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class MyMassage extends AppCompatActivity {

    EditText username;
    EditText sex;
    EditText birthday;
    EditText constellation;
    EditText physique;
    EditText grade;
    EditText college;
    EditText administrativeclass;
    EditText school;
    Button button5;
    ImageView imageView45;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_massage);
        username = findViewById(R.id.username);
        sex = findViewById(R.id.sex);
        birthday = findViewById(R.id.birthday);
        constellation = findViewById(R.id.constellation);
        physique = findViewById(R.id.physique);
        grade = findViewById(R.id.grade);
        college = findViewById(R.id.college);
        administrativeclass = findViewById(R.id.administrativeclass);
        school = findViewById(R.id.school);
        button5 = findViewById(R.id.button5);
        imageView45 = findViewById(R.id.imageView45);
        findViewById(R.id.gif15).setBackgroundResource(WeatherInformation.background);
        imageView45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String name =  sharedPreferences.getString("username","游客");
        username.setText(name);
        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findUserByUsername");//访问的请求地址
        requestParams.addQueryStringParameter("username",name);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject1 = new JSONObject(result);
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                    JSONObject jsonObject3 = jsonObject2.getJSONObject("education");
                    String physique1 = jsonObject2.getString("physique");
                    String birthday1 = jsonObject2.getString("birthday");
                    String sex1 = jsonObject2.getString("sex");
                    String constellation1 = jsonObject2.getString("constellation");
                    String school1 = jsonObject3.getString("school");
                    String grade1 = jsonObject3.getString("grade");
                    String college1 = jsonObject3.getString("college");
                    String administrativeclass1 = jsonObject3.getString("administrativeclass");
                    physique.setText(physique1);
                    birthday.setText(birthday1);
                    sex.setText(sex1);
                    constellation.setText(constellation1);
                    school.setText(school1);
                    grade.setText(grade1);
                    college.setText(college1);
                    administrativeclass.setText(administrativeclass1);

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams requestParams1 = new RequestParams("http://dvwbxngn.dnat.tech/updateUser");//访问的请求地址
                requestParams1.addQueryStringParameter("username",username.getText().toString());
                requestParams1.addQueryStringParameter("birthday", Date.valueOf(birthday.getText().toString()));
                requestParams1.addQueryStringParameter("sex",sex.getText().toString());
                requestParams1.addQueryStringParameter("school",school.getText().toString());
                requestParams1.addQueryStringParameter("grade",grade.getText().toString());
                requestParams1.addQueryStringParameter("college",college.getText().toString());
                requestParams1.addQueryStringParameter("administrativeclass",administrativeclass.getText().toString());
                requestParams1.addQueryStringParameter("location"," ");
                requestParams1.addQueryStringParameter("physique",physique.getText().toString());
                x.http().post(requestParams1, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            if(code == 0){
                                new AlertDialog.Builder(
                                        MyMassage.this)
                                        .setTitle("提示")
                                        .setMessage("修改成功！")
                                        .setPositiveButton("确定", null).show();
                            }
                            else {
                                new AlertDialog.Builder(
                                        MyMassage.this)
                                        .setTitle("提示")
                                        .setMessage("修改失败！")
                                        .setPositiveButton("确定", null).show();
                            }
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
        });

    }
}