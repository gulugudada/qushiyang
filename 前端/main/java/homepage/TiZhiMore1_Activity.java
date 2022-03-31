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
import com.hnucm.qushiyang.entity.Chat3;
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

public class TiZhiMore1_Activity extends AppCompatActivity {

    ImageView imageView17;
    TextView textView6;
    RecyclerView recycler5;
    List<Integer> list3;
    List<Chat3> chat3s;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_zhi_more1);
        Intent intent = this.getIntent();
        String tizhi = intent.getStringExtra("tizhi");
        textView6 = findViewById(R.id.textView6);
        textView6.setText(tizhi);
        list3 = new ArrayList<Integer>();
        list3.add(com.hnucm.qushiyang.R.drawable.more1);
        list3.add(com.hnucm.qushiyang.R.drawable.more2);
        list3.add(com.hnucm.qushiyang.R.drawable.more3);
        list3.add(com.hnucm.qushiyang.R.drawable.more4);
        findViewById(R.id.gif9).setBackgroundResource(WeatherInformation.background);
        recycler5 = findViewById(R.id.recycler5);
        Banner banner = findViewById(com.hnucm.qushiyang.R.id.banner2);
        banner.setAdapter(new BannerImageAdapter<Integer>(list3) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                holder.imageView.setImageResource(data);
            }
        });
        imageView17 = findViewById(R.id.imageView17);
        imageView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        x.Ext.init(getApplication());
        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findCookbookByPhysique");//访问的请求地址
        requestParams.addQueryStringParameter("physique",tizhi);
        chat3s = new ArrayList<>();
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i).getJSONObject("dish");
                        Chat3 chat = new Chat3();
                        String dishname = jsonObject1.getString("dishname");
//                        String stock = jsonObject1.getString("stock");
//                        String method = jsonObject1.getString("method");
                        String effect = jsonObject1.getString("effect");
                        String image = jsonObject1.getString("image");
                        chat.dishname = dishname;
//                        chat.stock = stock;
                        chat.effect = effect;
//                        chat.method = method;
                        chat.image1 = "http://dvwbxngn.dnat.tech/" + image;
                        chat3s.add(chat);
                        Log.i("AppLogon","结果"+dishname);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdapter = new MyAdapter();
                recycler5.setAdapter(myAdapter);
                recycler5.setLayoutManager(new LinearLayoutManager(TiZhiMore1_Activity.this));

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
            View view = LayoutInflater.from(TiZhiMore1_Activity.this).inflate(R.layout.item_list1, parent, false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
//            为什么不return一个view，要return一个MyViewHolder
            return myViewHolder;
        }
//        给布局里面的控件赋值
//        position 当前列表的第几条

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.dishname.setText(chat3s.get(position).getDishname());
            holder.effect.setText(chat3s.get(position).getEffect());
            Glide.with(TiZhiMore1_Activity.this).load(chat3s.get(position).image1).apply(RequestOptions.bitmapTransform(new RoundedCorners(50))).into(holder.imageView);
        }

        //表示显示数据的条数
        @Override
        public int getItemCount() {
            return chat3s.size();
        }
    }

    //回收利用，节约内存
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView dishname ;
        TextView effect;
        ImageView imageView;
        ConstraintLayout xiangqing;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dishname=itemView.findViewById(R.id.textView11);
            effect=itemView.findViewById(R.id.textView14);
            imageView=itemView.findViewById(R.id.imageView6);
            xiangqing=itemView.findViewById(R.id.xiangqing);

            xiangqing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TiZhiMore1_Activity.this,DishMassage.class);
                    intent.putExtra("dishname",dishname.getText().toString());
                    startActivity(intent);

                }
            });

        }
    }
}