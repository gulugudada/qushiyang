package com.hnucm.qushiyang.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.hnucm.qushiyang.entity.Chat1;
import com.hnucm.qushiyang.fragment.Main_Fragment3;
import com.hnucm.qushiyang.utils.WeatherInformation;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class SeasonMore1_Activity extends AppCompatActivity {
    ImageView imageView14;
    List<Integer> list2;
    TextView textView23;
    ImageView imageView18;
    RecyclerView recycler4;
    List<Chat1> chat1s;
    MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.hnucm.qushiyang.R.layout.activity_season_more1);
        Intent intent = this.getIntent();
        String season = intent.getStringExtra("season");

        list2 = new ArrayList<Integer>();
        list2.add(com.hnucm.qushiyang.R.drawable.more1);
        list2.add(com.hnucm.qushiyang.R.drawable.more2);
        list2.add(com.hnucm.qushiyang.R.drawable.more3);
        list2.add(com.hnucm.qushiyang.R.drawable.more4);
        Banner banner = findViewById(com.hnucm.qushiyang.R.id.banner1);
        banner.setAdapter(new BannerImageAdapter<Integer>(list2) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                holder.imageView.setImageResource(data);
            }
        });
        imageView14 = findViewById(com.hnucm.qushiyang.R.id.imageView14);
        textView23 = findViewById(com.hnucm.qushiyang.R.id.textView23);
        imageView18 = findViewById(com.hnucm.qushiyang.R.id.imageView18);
        recycler4 = findViewById(com.hnucm.qushiyang.R.id.recycler4);
        findViewById(R.id.gif7).setBackgroundResource(WeatherInformation.background);
        textView23.setText(season);
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findSeasonTuiJianBySeason");//?????????????????????
        requestParams.addQueryStringParameter("season", season);
        chat1s = new ArrayList<>();
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("AppLogon","??????"+result);
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
                recycler4.setAdapter(myAdapter);
                recycler4.setLayoutManager(new LinearLayoutManager(SeasonMore1_Activity.this));

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
        //????????????
        imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SeasonMore1_Activity.this,SeasonMore2_Activity.class);
                intent1.putExtra("season",season);
                startActivity(intent1);

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
            View view = LayoutInflater.from(SeasonMore1_Activity.this).inflate(R.layout.item_list1, parent, false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
//            ????????????return??????view??????return??????MyViewHolder
            return myViewHolder;
        }
//        ??????????????????????????????
//        position ????????????????????????

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.cookbook.setText(chat1s.get(position).getCookbook());
            holder.effect.setText("");
            Glide.with(SeasonMore1_Activity.this).load(chat1s.get(position).image).apply(RequestOptions.bitmapTransform(new RoundedCorners(50))).into(holder.imageView);
        }

        //???????????????????????????
        @Override
        public int getItemCount() {
            return chat1s.size();
        }
    }

    //???????????????????????????
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
                    Intent intent = new Intent(SeasonMore1_Activity.this,CookbookMassage.class);
                    intent.putExtra("cookbook",cookbook.getText().toString());
                    startActivity(intent);

                }
            });

        }
    }
}