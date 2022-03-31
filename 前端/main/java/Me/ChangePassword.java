package com.hnucm.qushiyang.Me;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.homepage.CookbookMassage;
import com.hnucm.qushiyang.login.LoginActivity;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ChangePassword extends AppCompatActivity {
    Button button;
    Button button2;
    Button button3;
    ImageView imageView40;
    Button button4;
    Bitmap bitmap;
    Bitmap bitmap1;
    ImageView imageView4;
    EditText pass1;
    EditText pass2;
    EditText pass3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        findViewById(R.id.gif10).setBackgroundResource(WeatherInformation.background);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        imageView4 = findViewById(R.id.imageView4);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        pass3 = findViewById(R.id.pass3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
                String account =  sharedPreferences.getString("phonenumber","账号");
                if(pass1.getText().toString().equals("")||pass2.getText().toString().equals("")||pass3.getText().toString().equals(""))
                {
                    new AlertDialog.Builder(
                            ChangePassword.this)
                            .setTitle("提示")
                            .setMessage("请将修改信息补充完整!")
                            .setPositiveButton("确定", null).show();
                }
                else if(!pass2.getText().toString().equals(pass3.getText().toString())){
                    new AlertDialog.Builder(
                            ChangePassword.this)
                            .setTitle("提示")
                            .setMessage("两次密码不一致！")
                            .setPositiveButton("确定", null).show();
                }
                else {
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/updatePasswordByAccount");//访问的请求地址
                    requestParams.addQueryStringParameter("account",account);
                    requestParams.addQueryStringParameter("password",pass3.getText().toString());


                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject1 = new JSONObject(result);
                                String msg = jsonObject1.getString("msg");
                                new AlertDialog.Builder(
                                        ChangePassword.this)
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
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageView40 = findViewById(R.id.imageView40);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //处理返回集
                startActivityForResult(intent, 1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getImage = new Intent(Intent.ACTION_PICK, null);
                getImage.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//这是图片类型
                startActivityForResult(getImage, 2);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://拍照处理
                //将拍下来的照片解析成Bitmap对象并显示
                bitmap = (Bitmap) data.getExtras().get("data");
                imageView40.setImageBitmap(bitmap);
                break;
            case 2://相册处理
                Uri path = data.getData();
                //利用框架加载图片资源
                Glide.with(this).load(path).into(imageView40);
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    public String bitmapToString(Bitmap bitmap){
        //将Bitmap转换成字符串
        String string=null;
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bStream);
        byte[]bytes=bStream.toByteArray();
        string=Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

}