package com.hnucm.qushiyang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.hnucm.qushiyang.login.LoginActivity;
import com.hnucm.qushiyang.questionnaire.QuestionnaireActivity1;

import org.xutils.x;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        x.Ext.init(getApplication());//网络xutils初始化
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /**
                 * 延时执行的代码
                 */
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
                if(!isLogin)
                {
//                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);  //start跳转
                }
                else {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);  //start跳转
                }
                WelcomeActivity.this.finish();//结束当前activity
            }
        },2000); // 延时2秒
    }
}