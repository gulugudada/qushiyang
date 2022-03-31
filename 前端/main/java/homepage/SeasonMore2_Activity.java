package com.hnucm.qushiyang.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.utils.WeatherInformation;

public class SeasonMore2_Activity extends AppCompatActivity {

    ImageView imageView20;
    ConstraintLayout constraintLayout24;
    ConstraintLayout constraintLayout29;
    ConstraintLayout constraintLayout30;
    ConstraintLayout constraintLayout31;
    TextView textView27;
    TextView textView28;
    TextView textView29;
    TextView textView30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_more2);
        imageView20 = findViewById(R.id.imageView20);
        imageView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        constraintLayout24 = findViewById(R.id.constraintLayout24);
        constraintLayout29 = findViewById(R.id.constraintLayout29);
        constraintLayout30 = findViewById(R.id.constraintLayout30);
        constraintLayout31 = findViewById(R.id.constraintLayout31);
        textView27 = findViewById(R.id.textView27);
        textView28 = findViewById(R.id.textView28);
        textView29 = findViewById(R.id.textView29);
        textView30 = findViewById(R.id.textView30);
        findViewById(R.id.gif8).setBackgroundResource(WeatherInformation.background);
        constraintLayout24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeasonMore2_Activity.this,AllSeasonMoreActivity.class);
                intent.putExtra("season",textView27.getText().toString());
                startActivity(intent);
            }
        });
        constraintLayout29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeasonMore2_Activity.this,AllSeasonMoreActivity.class);
                intent.putExtra("season",textView28.getText().toString());
                startActivity(intent);
            }
        });
        constraintLayout30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeasonMore2_Activity.this,AllSeasonMoreActivity.class);
                intent.putExtra("season",textView29.getText().toString());
                startActivity(intent);
            }
        });
        constraintLayout31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeasonMore2_Activity.this,AllSeasonMoreActivity.class);
                intent.putExtra("season",textView30.getText().toString());
                startActivity(intent);
            }
        });



    }
}