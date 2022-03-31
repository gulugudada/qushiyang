package com.hnucm.qushiyang.Me;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.login.LoginActivity;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class FeedBack extends AppCompatActivity {
    ImageView imageView43;
    EditText add_content;
    Button xiugai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        findViewById(R.id.gif11).setBackgroundResource(WeatherInformation.background);
        imageView43 = findViewById(R.id.imageView43);
        add_content = findViewById(R.id.add_content);
        xiugai = findViewById(R.id.xiugai);
        imageView43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                String name =  sharedPreferences.getString("username","游客");
                RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/addFanKui");//访问的请求地址
                requestParams.addQueryStringParameter("username", name);
                requestParams.addQueryStringParameter("fankui", add_content.getText().toString());
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        new AlertDialog.Builder(
                                FeedBack.this)
                                .setTitle("提示")
                                .setMessage(result)
                                .setPositiveButton("确定", null).show();
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
                add_content.setText("");
            }
        });

    }
}