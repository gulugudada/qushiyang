package com.hnucm.qushiyang.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.entity.Chat1;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class AllSeasonMoreActivity extends AppCompatActivity {
    ImageView imageView25;
    TextView textView31;
    TextView textView32;
    RecyclerView recycle5;
    List<Chat1> chat1s;
    MyAdapter myAdapter;
    ImageView imageView27;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_season_more);
        imageView25 = findViewById(R.id.imageView25);
        textView31 = findViewById(R.id.textView31);
        textView32 = findViewById(R.id.textView32);
        recycle5 = findViewById(R.id.recycle5);
        imageView27 = findViewById(R.id.imageView27);
        findViewById(R.id.gif1).setBackgroundResource(WeatherInformation.background);

        Intent intent = this.getIntent();
        String season = intent.getStringExtra("season");
        RequestParams requestParams1 = new RequestParams("http://dvwbxngn.dnat.tech/findSeasonBySeason");//访问的请求地址
        requestParams1.addQueryStringParameter("season", season);
        x.http().post(requestParams1, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = null;
                            jsonObject = new JSONObject(result);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            Glide.with(AllSeasonMoreActivity.this).load("http://dvwbxngn.dnat.tech/"+ jsonObject1.getString("image")).into(imageView27);
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


        imageView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView31.setText(season);
        textView32.setText(season+"推荐");
        x.Ext.init(getApplication());
        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findSeasonTuiJianBySeason");//访问的请求地址
        requestParams.addQueryStringParameter("season", season);
        chat1s = new ArrayList<>();
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Chat1 chat = new Chat1();
                        String cookbook = jsonArray.getJSONObject(i).getString("cookbook");
                        String imageView = jsonArray.getJSONObject(i).getString("image");
                        chat.cookbook = cookbook;
                        chat.image = "http://dvwbxngn.dnat.tech/" + imageView;
                        chat1s.add(chat);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdapter = new MyAdapter();
                recycle5.setAdapter(myAdapter);
                recycle5.setLayoutManager(new LinearLayoutManager(AllSeasonMoreActivity.this));

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
    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        //        加载布局
        @NonNull
        @Override
//        数据滚动后，会将屏幕外面的数据回收，然后创建下面的新的数据
//        存的数据是页面的数加1，还有一个等待回收的数据
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(AllSeasonMoreActivity.this).inflate(R.layout.item_list1, parent, false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
//            为什么不return一个view，要return一个MyViewHolder
            return myViewHolder;
        }
//        给布局里面的控件赋值
//        position 当前列表的第几条

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.cookbook.setText(chat1s.get(position).getCookbook());
            holder.effect.setText("");
            Glide.with(AllSeasonMoreActivity.this).load(chat1s.get(position).image).apply(RequestOptions.bitmapTransform(new RoundedCorners(50))).into(holder.imageView);
        }

        //表示显示数据的条数
        @Override
        public int getItemCount() {
            return chat1s.size();
        }
    }

    //回收利用，节约内存
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cookbook ;
        TextView effect;
        ImageView imageView;
        ConstraintLayout xiangqing;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cookbook=itemView.findViewById(R.id.textView11);
            effect=itemView.findViewById(R.id.textView14);
            imageView=itemView.findViewById(R.id.imageView6);
            xiangqing=itemView.findViewById(R.id.xiangqing);
            xiangqing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AllSeasonMoreActivity.this,CookbookMassage.class);
                    intent.putExtra("cookbook",cookbook.getText().toString());
                    startActivity(intent);

                }
            });

        }
    }
}