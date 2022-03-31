package com.hnucm.qushiyang.Me;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.hnucm.qushiyang.homepage.CookbookMassage;
import com.hnucm.qushiyang.homepage.DishMassage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class Collection2 extends Fragment {
    RecyclerView recycler7;
    List<Chat1> chat1s;
    MyAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection2, container, false);
        recycler7 = view.findViewById(R.id.recycler7);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
        String name =  sharedPreferences.getString("username","游客");
        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findCollectByUsername");//访问的请求地址
        requestParams.addQueryStringParameter("username",name);
        chat1s = new ArrayList<>();
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject1 = new JSONObject(result);
                    JSONArray jsonArray = jsonObject1.getJSONArray("tizhi");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Chat1 chat = new Chat1();
                        String cookbook = jsonArray.getJSONObject(i).getString("dishname");
                        String imageView = jsonArray.getJSONObject(i).getString("image");
                        chat.cookbook = cookbook;
                        chat.image = "http://dvwbxngn.dnat.tech/" + imageView;
                        chat1s.add(chat);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdapter = new MyAdapter();
                recycler7.setAdapter(myAdapter);
                recycler7.setLayoutManager(new LinearLayoutManager(getActivity()));
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
        return view;
    }
    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        //        加载布局
        @NonNull
        @Override
//        数据滚动后，会将屏幕外面的数据回收，然后创建下面的新的数据
//        存的数据是页面的数加1，还有一个等待回收的数据
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_list1, parent, false);
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
            Glide.with(getActivity()).load(chat1s.get(position).image).apply(RequestOptions.bitmapTransform(new RoundedCorners(50))).into(holder.imageView);
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
                    Intent intent = new Intent(getActivity(), DishMassage.class);
                    intent.putExtra("dishname",cookbook.getText().toString());
                    startActivity(intent);

                }
            });

        }
    }
}