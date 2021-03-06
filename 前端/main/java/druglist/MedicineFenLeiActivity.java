package com.hnucm.qushiyang.druglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.entity.Chat2;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MedicineFenLeiActivity extends AppCompatActivity {
    ImageView imageView7;
    TextView textView10;
    RecyclerView recyclerView;
    List<Chat2> list;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_fen_lei);
        Intent intent = this.getIntent();
        String medicineclass = intent.getStringExtra("medicineclass");
        textView10 = findViewById(R.id.textView10);
        textView10.setText(medicineclass);
        imageView7 = findViewById(R.id.imageView7);
        findViewById(R.id.gif5).setBackgroundResource(WeatherInformation.background);
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        x.Ext.init(getApplication());
        RequestParams requestParams1 = new RequestParams("http://dvwbxngn.dnat.tech/getMedicineClass");
        requestParams1.addParameter("Class",medicineclass);
        list = new ArrayList<>();
        Log.i("AppLogon",requestParams1.toString());
        recyclerView = findViewById(R.id.recyclerView3);
        x.http().post(requestParams1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("AppLogon","??????"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        Chat2 chat2 = new Chat2();
                        String ChineseName = jsonArray.getJSONObject(i).getString("chineseName");
                        String Image = jsonArray.getJSONObject(i).getString("image");
                        chat2.ChineseName = ChineseName;
                        chat2.Image = "http://dvwbxngn.dnat.tech/"+Image;
                        list.add(chat2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdapter = new MyAdapter();
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MedicineFenLeiActivity.this,2));
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("AppLogon", String.valueOf(ex));
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.i("AppLogon","onCancelled");
            }

            @Override
            public void onFinished() {
                Log.i("AppLogon","onFinished");
            }
        });

    }
    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        //        ????????????
        @NonNull
        @Override
//        ???????????????????????????????????????????????????????????????????????????????????????
//        ??????????????????????????????1????????????????????????????????????
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MedicineFenLeiActivity.this).inflate(R.layout.item_list2, parent, false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
//            ????????????return??????view??????return??????MyViewHolder
            return myViewHolder;
        }
//        ??????????????????????????????
//        position ????????????????????????

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.ChineseName.setText(list.get(position).getChineseName());
            Glide.with(MedicineFenLeiActivity.this).load(list.get(position).Image).apply(RequestOptions.bitmapTransform(new RoundedCorners(50))).into(holder.Image);
        }

        //???????????????????????????
        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    //???????????????????????????
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ChineseName;
        ImageView Image;
        ConstraintLayout constraintLayout15;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ChineseName=itemView.findViewById(R.id.textView12);
            Image=itemView.findViewById(R.id.imageView8);
            constraintLayout15=itemView.findViewById(R.id.constraintLayout15);
            constraintLayout15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MedicineFenLeiActivity.this,MedicineDetailsActivity.class);
                    intent.putExtra("ChineseName",ChineseName.getText().toString());
                    startActivity(intent);
                }
            });

        }
    }
}