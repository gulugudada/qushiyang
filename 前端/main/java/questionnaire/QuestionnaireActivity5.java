package com.hnucm.qushiyang.questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.hnucm.qushiyang.R;

public class QuestionnaireActivity5 extends AppCompatActivity {
    private Button button;
    private Button button1;
    private RadioGroup radio1;
    private RadioGroup radio2;
    private RadioGroup radio3;
    private RadioGroup radio4;
    private RadioGroup radio5;
    private RadioGroup radio6;
    private RadioGroup radio7;
    int a = 0;
    int a1 = 0;
    int a2 = 0;
    int a3 = 0;
    int a4 = 0;
    int a5 = 0;
    int a6 = 0;
    int a7 = 0;
    String fenshu1 = "";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire5);
        ActionBar actionBar = getSupportActionBar();     //取消标题头actionbar
        if (actionBar != null) {
            actionBar.hide();
        }


        button = findViewById(R.id.btn4);
        button1 = findViewById(R.id.fenhui3);
        radio1= findViewById(R.id.gender32);
        radio2= findViewById(R.id.gender33);
        radio3= findViewById(R.id.gender34);
        radio4= findViewById(R.id.gender35);
        radio5= findViewById(R.id.gender36);
        radio6= findViewById(R.id.gender37);
        radio7= findViewById(R.id.gender38);

        Intent intent = this.getIntent();
        String ONE = intent.getStringExtra("one");
        String TOW = intent.getStringExtra("tow");
        String THREE = intent.getStringExtra("three");
        String FOUR = intent.getStringExtra("four");
        String Name = intent.getStringExtra("name");




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((radio1.getCheckedRadioButtonId()==-1||radio2.getCheckedRadioButtonId()==-1)||(radio3.getCheckedRadioButtonId()==-1||radio4.getCheckedRadioButtonId()==-1
                        ||radio5.getCheckedRadioButtonId()==-1||radio6.getCheckedRadioButtonId()==-1||radio7.getCheckedRadioButtonId()==-1)){
                    Toast.makeText(QuestionnaireActivity5.this,"请完成问卷",Toast.LENGTH_LONG).show();
                }else{

                    Intent intent = new Intent(QuestionnaireActivity5.this, QuestionnaireActivity6.class);

                    RadioButton radioButton1 = findViewById(radio1.getCheckedRadioButtonId());
                    a = fenshu(radioButton1.getText().toString());

                    RadioButton radioButton2 = findViewById(radio2.getCheckedRadioButtonId());
                    a1 = fenshu(radioButton2.getText().toString());

                    RadioButton radioButton3 = findViewById(radio3.getCheckedRadioButtonId());
                    a2 = fenshu(radioButton3.getText().toString());

                    RadioButton radioButton4 = findViewById(radio4.getCheckedRadioButtonId());
                    a3 = fenshu(radioButton4.getText().toString());

                    RadioButton radioButton5 = findViewById(radio5.getCheckedRadioButtonId());
                    a4 = fenshu(radioButton5.getText().toString());

                    RadioButton radioButton6 = findViewById(radio6.getCheckedRadioButtonId());
                    a5 = fenshu(radioButton6.getText().toString());

                    RadioButton radioButton7 = findViewById(radio7.getCheckedRadioButtonId());
                    a6 = fenshu(radioButton7.getText().toString());



                    a7 = a+a1+a2+a3+a4+a5+a6;

                    fenshu1 = ""+a7;

                    intent.putExtra("one", ONE);
                    intent.putExtra("tow", TOW);
                    intent.putExtra("three",THREE);
                    intent.putExtra("four",FOUR);
                    intent.putExtra("five",fenshu1);
                    intent.putExtra("name", Name);

                    startActivity(intent);
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent1 = new Intent(Test5.this,Test4.class);
//                startActivity(intent1);
            }
        });


    }

    public int fenshu(String b){
        if(b.equals("总是")){
            return 5;
        }
        else if(b.equals("经常")){
            return 4;
        }
        else if(b.equals("有时")){
            return 3;
        }
        else if(b.equals("很少")){
            return 2;
        }
        else {
            return 1;
        }
    }
}