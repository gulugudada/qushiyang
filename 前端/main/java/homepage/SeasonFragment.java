package com.hnucm.qushiyang.homepage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.utils.AddressInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SeasonFragment extends Fragment {
    public Activity mActivity;
    RecyclerView recyclerView;
    ConstraintLayout more1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_season, container, false);
        TextView season = view.findViewById(R.id.textView19);
        TextView reason = view.findViewById(R.id.textView22);
        ImageView image = view.findViewById(R.id.imageView2);
        TextView vegetables = view.findViewById(R.id.vegtable);
        TextView fruit = view.findViewById(R.id.fruit);
        TextView menu = view.findViewById(R.id.food);
        TextView soup = view.findViewById(R.id.soup);
        recyclerView = view.findViewById(R.id.rec);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Calendar c = Calendar.getInstance();
        Date curDate = new Date(System.currentTimeMillis());
        c.setTime(curDate);
        season.setText(getSeason(formatter.format(curDate).split(" ")[0]));
        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findSeasonBySeason");
        requestParams.addQueryStringParameter("season",getSeason(formatter.format(curDate).split(" ")[0]));
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @SuppressLint({"CheckResult", "SetTextI18n"})
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    reason.setText("      "+jsonObject1.getString("reason"));
                    Glide.with(mActivity).load("http://dvwbxngn.dnat.tech/"+ jsonObject1.getString("image")).into(image);
                    vegetables.setText(jsonObject1.getString("vegetables"));
                    fruit.setText(jsonObject1.getString("fruit"));
                    menu.setText(jsonObject1.getString("menu"));
                    soup.setText(jsonObject1.getString("soup"));

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
        more1 = view.findViewById(R.id.more1);
        more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SeasonMore1_Activity.class);
                intent.putExtra("season",season.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }

    public String getSeason(String date){
        String month = date.split("年")[1].split("月")[0];
        switch (month){
            case "12":
            case "1":
            case "2":return "冬季";
            case "3":
            case "4":
            case "5":return "春季";
            case "6":
            case "7":
            case "8":return "夏季";
            case "9":
            case "10":
            case "11":return "秋季";
        }
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }
}