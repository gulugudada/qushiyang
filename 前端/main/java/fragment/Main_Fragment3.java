package com.hnucm.qushiyang.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.druglist.MedicineDetailsActivity;
import com.hnucm.qushiyang.druglist.MedicineFenLeiActivity;
import com.hnucm.qushiyang.druglist.MedicineSearchActivity;
import com.hnucm.qushiyang.entity.Chat2;
import com.hnucm.qushiyang.homepage.WeatherActivity;
import com.hnucm.qushiyang.utils.Slidemenu;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class Main_Fragment3 extends Fragment {
    private Activity mActivity;
    EditText editText;
    ImageView imageView2;
    RecyclerView recyclerView;
    List<Chat2> list;
    MyAdapter myAdapter;
    List<Integer> list1;
    Slidemenu slidemenu;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    TextView serchText;
    ConstraintLayout constraintLayout1;
    ConstraintLayout constraintLayout2;
    ConstraintLayout constraintLayout3;
    ConstraintLayout constraintLayout4;
    ConstraintLayout constraintLayout5;
    ConstraintLayout constraintLayout6;
    ConstraintLayout constraintLayout7;
    ConstraintLayout constraintLayout8;
    ConstraintLayout constraintLayout9;
    ConstraintLayout constraintLayout10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_3, container, false);
        list1 = new ArrayList<Integer>();
        list1.add(R.drawable.y1);
        list1.add(R.drawable.y2);
        list1.add(R.drawable.y3);
        list1.add(R.drawable.y4);
        list1.add(R.drawable.y5);
        Banner banner = view.findViewById(R.id.banner);
        banner.setAdapter(new BannerImageAdapter<Integer>(list1) {
            @Override
            public void onBindView(BannerImageHolder holder, Integer data, int position, int size) {
                holder.imageView.setImageResource(data);
            }
        });
        banner.setIndicator(new CircleIndicator(mActivity));
        slidemenu=view.findViewById(R.id.Menu);
        imageView2=view.findViewById(R.id.imageView11);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidemenu.switchMenu();
            }
        });
        textView2 = view.findViewById(R.id.jiebiao);
        textView3 = view.findViewById(R.id.qingre);
        textView4 = view.findViewById(R.id.qufengshi);
        textView5 = view.findViewById(R.id.xiexia);
        textView6 = view.findViewById(R.id.zhixue);
        textView7 = view.findViewById(R.id.huoxue);
        textView8 = view.findViewById(R.id.anshen);
        textView9 = view.findViewById(R.id.buxu);
        textView10 = view.findViewById(R.id.shouse);
        textView11 = view.findViewById(R.id.quchong);
        constraintLayout1 = view.findViewById(R.id.con100);
        constraintLayout2 = view.findViewById(R.id.con200);
        constraintLayout3 = view.findViewById(R.id.con300);
        constraintLayout4 = view.findViewById(R.id.con400);
        constraintLayout5 = view.findViewById(R.id.con500);
        constraintLayout6 = view.findViewById(R.id.con600);
        constraintLayout7 = view.findViewById(R.id.con700);
        constraintLayout8 = view.findViewById(R.id.con800);
        constraintLayout9 = view.findViewById(R.id.con900);
        constraintLayout10 = view.findViewById(R.id.con1000);
        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#FF2196F3"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setTextColor(Color.parseColor("#000000"));

                String medicineclass = textView2.getText().toString();
                Intent intent = new Intent(mActivity, MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);

            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#FF2196F3"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setTextColor(Color.parseColor("#000000"));
                String medicineclass = textView3.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#FF2196F3"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setTextColor(Color.parseColor("#000000"));
                String medicineclass = textView4.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#FF2196F3"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setTextColor(Color.parseColor("#000000"));
                String medicineclass = textView5.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        constraintLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#FF2196F3"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setBackgroundColor(Color.parseColor("#000000"));
                String medicineclass = textView6.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        constraintLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#FF2196F3"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setTextColor(Color.parseColor("#000000"));
                String medicineclass = textView7.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        constraintLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#FF2196F3"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setTextColor(Color.parseColor("#000000"));
                String medicineclass = textView8.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        constraintLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#FF2196F3"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setTextColor(Color.parseColor("#000000"));
                String medicineclass = textView9.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        constraintLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#FF2196F3"));
                textView11.setTextColor(Color.parseColor("#000000"));
                String medicineclass = textView10.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        constraintLayout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(Color.parseColor("#000000"));
                textView3.setTextColor(Color.parseColor("#000000"));
                textView4.setTextColor(Color.parseColor("#000000"));
                textView5.setTextColor(Color.parseColor("#000000"));
                textView6.setTextColor(Color.parseColor("#000000"));
                textView7.setTextColor(Color.parseColor("#000000"));
                textView8.setTextColor(Color.parseColor("#000000"));
                textView9.setTextColor(Color.parseColor("#000000"));
                textView10.setTextColor(Color.parseColor("#000000"));
                textView11.setTextColor(Color.parseColor("#FF2196F3"));
                String medicineclass = textView11.getText().toString();
                Intent intent = new Intent(mActivity,MedicineFenLeiActivity.class);
                intent.putExtra("medicineclass", medicineclass);
                startActivity(intent);
            }
        });
        editText = view.findViewById(R.id.searchView);
        serchText = view.findViewById(R.id.serchText);
        serchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(mActivity, MedicineSearchActivity.class);
                intent.putExtra("likename",editText.getText().toString());
                startActivity(intent);
            }
        });
        RequestParams requestParams1 = new RequestParams("http://dvwbxngn.dnat.tech/findAllDate");
        list = new ArrayList<>();
        Log.i("AppLogon",requestParams1.toString());
        recyclerView = view.findViewById(R.id.recyclerView);
        x.http().get(requestParams1, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("AppLogon","结果"+result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        Chat2 chat2 = new Chat2();
                        String ChineseName = jsonArray.getJSONObject(i).getString("chineseName");
                        String EfficacyAndFunction = jsonArray.getJSONObject(i).getString("efficacyAndFunction");
                        String Image = jsonArray.getJSONObject(i).getString("image");
                        chat2.ChineseName = ChineseName;
                        chat2.EfficacyAndFunction = EfficacyAndFunction;
                        chat2.Image = "http://dvwbxngn.dnat.tech/"+Image;
                        list.add(chat2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdapter = new MyAdapter();
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
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
        return view;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        //        加载布局
        @NonNull
        @Override
//        数据滚动后，会将屏幕外面的数据回收，然后创建下面的新的数据
//        存的数据是页面的数加1，还有一个等待回收的数据
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_list1, parent, false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
//            为什么不return一个view，要return一个MyViewHolder
            return myViewHolder;
        }
//        给布局里面的控件赋值
//        position 当前列表的第几条

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.ChineseName.setText(list.get(position).getChineseName());
            holder.EfficacyAndFunction.setText(list.get(position).getEfficacyAndFunction());
            Glide.with(mActivity).load(list.get(position).Image).apply(RequestOptions.bitmapTransform(new RoundedCorners(50))).into(holder.Image);
        }

        //表示显示数据的条数
        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    //回收利用，节约内存
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ChineseName;
        TextView EfficacyAndFunction;
        ImageView Image;
        ConstraintLayout xiangqing;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ChineseName=itemView.findViewById(R.id.textView11);
            EfficacyAndFunction=itemView.findViewById(R.id.textView14);
            Image=itemView.findViewById(R.id.imageView6);
            xiangqing = itemView.findViewById(R.id.xiangqing);
            xiangqing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MedicineDetailsActivity.class);
                    intent.putExtra("ChineseName",ChineseName.getText().toString());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }
}