package com.hnucm.qushiyang.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.questionnaire.QuestionnaireActivity1;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class RegisterActivity extends AppCompatActivity {
    String user;
    String pass;
    String vernumber;
    String phone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText phone = findViewById(R.id.phone1);
        EditText password = findViewById(R.id.pwdEt1);
        EditText verify = findViewById(R.id.verify);
        EditText username = findViewById(R.id.user);
        Button verbnt = findViewById(R.id.verBtn);
        Button registerbnt = findViewById(R.id.regBtn1);
        verbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone1 = phone.getText().toString();
                pass = password.getText().toString();
                vernumber = verify.getText().toString();
                user = username.getText().toString();
                if(user.equals(""))
                {
                    new AlertDialog.Builder(
                            RegisterActivity.this)
                            .setTitle("提示")
                            .setMessage("用户名不能为空！")
                            .setPositiveButton("确定", null).show();
                }
                else if(pass.equals(""))
                {
                    new AlertDialog.Builder(
                            RegisterActivity.this)
                            .setTitle("提示")
                            .setMessage("密码不能为空！")
                            .setPositiveButton("确定", null).show();
                }
                else if(phone1.equals(""))
                {
                    new AlertDialog.Builder(
                            RegisterActivity.this)
                            .setTitle("提示")
                            .setMessage("电话号码不能为空！")
                            .setPositiveButton("确定", null).show();
                }
                else {
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/getYanZhengMa");//访问的请求地址
                    requestParams.addQueryStringParameter("account", phone1);
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        int result1 = 0;
                        String yanzhengma = "";
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                result1 = jsonObject.getInt("code");
                                yanzhengma = jsonObject.getString("验证码");
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
        registerbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone1 = phone.getText().toString();
                pass = password.getText().toString();
                vernumber = verify.getText().toString();
                user = username.getText().toString();
                if(user.equals(""))
                {
                    new AlertDialog.Builder(
                            RegisterActivity.this)
                            .setTitle("提示")
                            .setMessage("电话号码不能为空！")
                            .setPositiveButton("确定", null).show();
                }
                else if(pass.equals(""))
                {
                    new AlertDialog.Builder(
                            RegisterActivity.this)
                            .setTitle("提示")
                            .setMessage("密码不能为空！")
                            .setPositiveButton("确定", null).show();
                }
                else {
//                    user = username.getText().toString();
//                    pass = password.getText().toString();
//                    phone1 = phone.getText().toString();
//                    vernumber = verify.getText().toString();
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/addAccount");//访问的请求地址
                    requestParams.addQueryStringParameter("username", user);
                    requestParams.addQueryStringParameter("password", pass);
                    requestParams.addQueryStringParameter("account", phone1);
                    requestParams.addQueryStringParameter("yanzhengma", vernumber);
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        String yanzhengma = "";
                        String msg = "";
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                msg = jsonObject.getString("msg");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i("Register",result);
                            Log.i("Register",msg);

//                            new AlertDialog.Builder(
//                                    Register.this)
//                                    .setTitle("提示")
//                                    .setMessage(msg)
//                                    .setPositiveButton("确定", null).show();
                            //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            //    设置Title的图标
                            builder.setIcon(R.drawable.star);
                            //    设置Title的内容
                            builder.setTitle("提示");
                            //    设置Content来显示一个信息
                            builder.setMessage(msg);
                            //    设置一个PositiveButton
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent intent = new Intent(RegisterActivity.this, QuestionnaireActivity1.class);
                                    intent.putExtra("name", user);
                                    startActivity(intent);

                                }
                            });
                            builder.setNeutralButton("取消", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Toast.makeText(RegisterActivity.this, "neutral: " + which, Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.show();

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