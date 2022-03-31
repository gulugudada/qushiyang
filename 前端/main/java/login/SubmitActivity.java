package com.hnucm.qushiyang.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.questionnaire.QuestionnaireActivity1;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class SubmitActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private Button button;
    private Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = this.getIntent();
        String ONE = intent.getStringExtra("one");
        String TOW = intent.getStringExtra("tow");
        String THREE = intent.getStringExtra("three");
        String FOUR = intent.getStringExtra("four");
        String FIVE = intent.getStringExtra("five");
        String SIX = intent.getStringExtra("six");
        String SEVEN = intent.getStringExtra("seven");
        String EIGHT = intent.getStringExtra("eight");
        String NINE = intent.getStringExtra("nine");
        String Name = intent.getStringExtra("name");
        x.Ext.init(getApplication());




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        textView1 = findViewById(R.id.one);
        textView1.setText("阳虚质:"+ONE);

        textView2 = findViewById(R.id.tow);
        textView2.setText("阴虚质:"+TOW);

        textView3 = findViewById(R.id.three);
        textView3.setText("气虚质:"+THREE);

        textView4 = findViewById(R.id.four);
        textView4.setText("痰湿质:"+FOUR);

        textView5 = findViewById(R.id.five);
        textView5.setText("湿热质:"+FIVE);

        textView5 = findViewById(R.id.six);
        textView5.setText("血瘀质:"+SIX);

        textView5 = findViewById(R.id.seven);
        textView5.setText("特禀质:"+SEVEN);

        textView5 = findViewById(R.id.eight);
        textView5.setText("气郁质:"+EIGHT);

        textView5 = findViewById(R.id.nine);
        textView5.setText("平和质:"+NINE);

        String result = MaoPao(ONE,TOW,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE);
        new AlertDialog.Builder(
                com.hnucm.qushiyang.login.SubmitActivity.this)
                .setTitle("提示")
                .setMessage(Name+",您的体质是:"+result)
                .setPositiveButton("确定", null).show();

        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/addUser2");//访问的请求地址
        requestParams.addQueryStringParameter("username", Name);
        requestParams.addQueryStringParameter("physique", result);
        //传后端参数
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

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

        button = findViewById(R.id.again_btn);
        button2 = findViewById(R.id.queding_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(com.hnucm.qushiyang.login.SubmitActivity.this, QuestionnaireActivity1.class);
                startActivity(intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(com.hnucm.qushiyang.login.SubmitActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });
    }

    public String MaoPao(String one,String tow,String three,String four,String five,String six,String seven,String eight,String nine){
        int[] a = new int[9];
        a[0] = Integer.parseInt(one);
        a[1] = Integer.parseInt(tow);
        a[2] = Integer.parseInt(three);
        a[3] = Integer.parseInt(four);
        a[4] = Integer.parseInt(five);
        a[5] = Integer.parseInt(six);
        a[6] = Integer.parseInt(seven);
        a[7] = Integer.parseInt(eight);
        a[8] = Integer.parseInt(nine);
        String[] b = new String[9];
        b[0] = "阳虚质";
        b[1] = "阴虚质";
        b[2] = "气虚质";
        b[3] = "痰湿质";
        b[4] = "湿热质";
        b[5] = "血瘀质";
        b[6] = "特禀质";
        b[7] = "气郁质";
        b[8] = "平和质";
        for (int i=0;i<8;i++)
        {
            for(int j=0;j<a.length-1-i;j++)
            {
                int tem = a[j+1];
                a[j+1] = a[j];
                a[j] = tem;

                String tem1 = b[j+1];
                b[j+1] = b[j];
                b[j] = tem1;
            }
        }
        return b[8];

    }
}