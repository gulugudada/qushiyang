package com.hnucm.qushiyang.Roubt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.entity.Msg;
import com.hnucm.qushiyang.utils.WeatherInformation;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class SessionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText editText;
    Button send;
    List<Msg> msgList = new ArrayList<Msg>();
    MyAdapter myAdapter;
    ImageView imageView55;

    public SessionActivity(){
        initMsgs();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        recyclerView = findViewById(R.id.recyclerView3);
        editText = findViewById(R.id.editTextTextPersonName5);
        send = findViewById(R.id.button5);
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        findViewById(R.id.gif16).setBackgroundResource(WeatherInformation.background);
        recyclerView.setLayoutManager(new LinearLayoutManager(SessionActivity.this));
        imageView55 = findViewById(R.id.imageView55);
        imageView55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息，刷新RecyclerVeiw的显示
                    myAdapter.notifyItemInserted(msgList.size() - 1);
                    //将RecyclerView定位到最后一行
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    //清空输入框内容
                    editText.setText("");
                    RequestParams requestParams = new RequestParams("http://dvwbxngn.dnat.tech/findEnquire");
                    requestParams.addQueryStringParameter("question",content );
                    x.http().post(requestParams, new Callback.CommonCallback<String>(){

                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                String data = jsonObject.getString("msg");
                                data = data.replace("\n","");
                                Msg msg = new Msg(data,Msg.TYPE_RECEIVED);
                                msgList.add(msg);
                                myAdapter.notifyItemInserted(msgList.size() - 1);
                                recyclerView.scrollToPosition(msgList.size() - 1);
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
                }
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SessionActivity.this).inflate(R.layout.msg_item,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Msg msg = msgList.get(position);
            if (msg.getType()==Msg.TYPE_RECEIVED){
                //如果是收到消息，则显示在左边，将右边布局隐藏
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                holder.leftMsg.setText(msg.getContent());

            }else if(msg.getType()==Msg.TYPE_SENT){
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.leftLayout.setVisibility(View.GONE);
                holder.rightMsg.setText(msg.getContent());
            }
        }

        @Override
        public int getItemCount() {
            return msgList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

        public MyViewHolder(View view){
            super(view);
            leftLayout = view.findViewById(R.id.left_layout);
            rightLayout = view.findViewById(R.id.right_layout);
            leftMsg = view.findViewById(R.id.left_msg);
            rightMsg = view.findViewById(R.id.right_msg);
        }
    }

    private void initMsgs(){
        Msg msg1 = new Msg("你好，我是美女小助手。",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
    }
}