package com.hnucm.qushiyang.homepage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hnucm.qushiyang.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class TiZhiFragment extends Fragment {
    TextView textView2;
    TextView textView4;
    TextView cause;
    TextView disease;
    TextView Yangsheng;
    TextView eatless;
    String imageurl;
    ImageView image;
    String show;
    String cause1;
    String disease1;
    String Yangsheng1;
    String eatless1;
    ConstraintLayout more2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ti_zhi, container, false);
        textView2 = view.findViewById(R.id.textView2);
        textView4 = view.findViewById(R.id.textView4);
        cause = view.findViewById(R.id.cause);
        disease = view.findViewById(R.id.disease);
        Yangsheng = view.findViewById(R.id.Yangsheng);
        eatless = view.findViewById(R.id.eatless);
        image = view.findViewById(R.id.image);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user2", Activity.MODE_PRIVATE);
        String constitution =  sharedPreferences.getString("constitution","体质");
        textView2.setText(constitution);
        more2 = view.findViewById(R.id.more2);
        more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TiZhiMore1_Activity.class);
                intent.putExtra("tizhi",constitution);
                startActivity(intent);
            }
        });
        x.Ext.init(getActivity().getApplication());
        RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findPhysique");//访问的请求地址
        requestParams.addQueryStringParameter("physiquename", constitution);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject  jsonObject = new JSONObject(result);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    show = jsonObject1.getString("show");
                    cause1 = jsonObject1.getString("cause");
                    disease1 = jsonObject1.getString("disease");
                    Yangsheng1 = jsonObject1.getString("yangsheng");
                    eatless1 = jsonObject1.getString("eatless");
                    imageurl = jsonObject1.getString("image");
                    Glide.with(getActivity()).load("http://dvwbxngn.dnat.tech/"+imageurl).into(TiZhiFragment.this.image);
                    textView4.setText(show);
                    cause.setText(cause1);
                    disease.setText(disease1);
                    Yangsheng.setText(Yangsheng1);
                    eatless.setText(eatless1);

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
        return view;
    }
}