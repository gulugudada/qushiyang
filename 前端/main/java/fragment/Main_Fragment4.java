package com.hnucm.qushiyang.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.hnucm.qushiyang.Me.ChangePassword;
import com.hnucm.qushiyang.Me.FeedBack;
import com.hnucm.qushiyang.Me.MyCollection;
import com.hnucm.qushiyang.Me.MyMassage;
import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.questionnaire.QuestionnaireActivity1;


public class Main_Fragment4 extends Fragment {
    ConstraintLayout constraintLayout9;     //设置
    ConstraintLayout constraintLayout10;    //收藏
    ConstraintLayout constraintLayout11;    //意见反馈
    ConstraintLayout constraintLayout13;    //退出
    ImageView imageView7;                   //头像
    TextView textView12;                    //用户名
    TextView textView13;                    //个人标签
    ConstraintLayout constraintLayout50;
    ConstraintLayout constraintLayout6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_4, container, false);
        imageView7 = view.findViewById(R.id.imageView7);
        constraintLayout11 = view.findViewById(R.id.constraintLayout11);
        constraintLayout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedBack.class);
                startActivity(intent);
            }
        });
        constraintLayout50 = view.findViewById(R.id.constraintLayout50);
        constraintLayout50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuestionnaireActivity1.class);
                startActivity(intent);
            }
        });
        constraintLayout6 = view.findViewById(R.id.constraintLayout6);
        constraintLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyMassage.class);
                startActivity(intent);
            }
        });


        textView12 = view.findViewById(R.id.textView12);
        constraintLayout13 = view.findViewById(R.id.constraintLayout13);
        constraintLayout9 = view.findViewById(R.id.constraintLayout9);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name =  sharedPreferences.getString("username","游客");
        constraintLayout10 = view.findViewById(R.id.constraintLayout10);
        constraintLayout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCollection.class);
                startActivity(intent);
            }
        });
//        boolean isLogin1 = sharedPreferences.getBoolean("isLogin1",false);
//        if(isLogin1 != false){
//            String Stringimage = savedInstanceState.getString("imageString","WU");
//            Bitmap bitmap = stringToBitmap(Stringimage);
//            System.out.println(Stringimage);
//            imageView7.setImageBitmap(bitmap);
//        }
        textView12.setText(name);

        constraintLayout13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("isLogin",false);
                editor.commit();
                System.exit(0);
            }
        });
        constraintLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });


        return view;

    }
    public Bitmap stringToBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}