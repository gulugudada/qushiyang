package com.hnucm.qushiyang.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hnucm.qushiyang.MainActivity;
import com.hnucm.qushiyang.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.
                permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.
                permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(LoginActivity.this,Manifest.
                permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String [] permissions = permissionList.toArray(new String[permissionList.
                    size()]);
            ActivityCompat.requestPermissions(LoginActivity.this,permissions,1);
        }
        EditText editText1= findViewById(R.id.accountEt);
        EditText editText2 = findViewById(R.id.pwdEt);
        Button login = findViewById(R.id.subBtn);
        Button register = findViewById(R.id.regBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText1.getText().toString();
                String password = editText2.getText().toString();
                if(username.equals(""))
                {
                    new AlertDialog.Builder(
                            LoginActivity.this)
                            .setTitle("提示")
                            .setMessage("请输入用户名！")
                            .setPositiveButton("确定", null).show();
                }
                else if(password.equals(""))
                {
                    new AlertDialog.Builder(
                            LoginActivity.this)
                            .setTitle("提示")
                            .setMessage("请输入密码！")
                            .setPositiveButton("确定", null).show();
                }
                else {
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findPasswordByAccount");//访问的请求地址
                    requestParams.addQueryStringParameter("account", username);
                    requestParams.addQueryStringParameter("password", password);
                    Log.i("AppLogon",username);
                    Log.i("AppLogon",requestParams.toString());
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            int result1 = -1;
                            String msg = "";
                            String name1 = "";
                            Log.i("AppLogon","结果"+result);
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                result1 = jsonObject.getInt("code");
                                msg = jsonObject.getString("msg");
                                name1 = jsonObject.getString("username");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if(result1 == 0)
                            {
                                Log.i("AppLogon",""+result1);
                                //进行存储信息
                                SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isLogin",true);//islogin设置为true代表已经登陆
                                editor.putString("phonenumber",username);
                                editor.putString("username",name1);
                                editor.putString("password",password);
                                editor.commit();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("name", username);
                                startActivity(intent);
                                finish();
                            }else {
                                new AlertDialog.Builder(
                                        LoginActivity.this)
                                        .setTitle("提示")
                                        .setMessage(msg)
                                        .setPositiveButton("确定", null).show();
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
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}