package com.hnucm.qushiyang.Me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.utils.WeatherInformation;

public class MyCollection extends AppCompatActivity {
    Collection1 collection1 = new Collection1();
    Collection2 collection2 = new Collection2();
    Collection3 collection3 = new Collection3();
    TextView textView54;
    TextView textView55;
    TextView textView56;
    ConstraintLayout constraintLayout46;
    ConstraintLayout constraintLayout47;
    ConstraintLayout constraintLayout48;
    ImageView imageView42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        findViewById(R.id.gif12).setBackgroundResource(WeatherInformation.background);
        textView54 = findViewById(R.id.textView54);
        textView55 = findViewById(R.id.textView55);
        textView56 = findViewById(R.id.textView56);
        constraintLayout46 = findViewById(R.id.constraintLayout46);
        constraintLayout47 = findViewById(R.id.constraintLayout47);
        constraintLayout48 = findViewById(R.id.constraintLayout48);
        imageView42 = findViewById(R.id.imageView42);
        imageView42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.con1,collection1).commit();
        textView54.setTextColor(Color.parseColor("#FFEB3B"));
        constraintLayout46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.con1,collection1).commit();
                textView54.setTextColor(Color.parseColor("#FFEB3B"));
                textView55.setTextColor(Color.parseColor("#000000"));
                textView56.setTextColor(Color.parseColor("#000000"));
            }
        });
        constraintLayout47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.con1,collection2).commit();
                textView54.setTextColor(Color.parseColor("#000000"));
                textView55.setTextColor(Color.parseColor("#FFEB3B"));
                textView56.setTextColor(Color.parseColor("#000000"));
            }
        });
        constraintLayout48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.con1,collection3).commit();
                textView54.setTextColor(Color.parseColor("#000000"));
                textView55.setTextColor(Color.parseColor("#000000"));
                textView56.setTextColor(Color.parseColor("#FFEB3B"));
            }
        });

    }
}