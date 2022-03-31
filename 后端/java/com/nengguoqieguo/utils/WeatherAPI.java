package com.nengguoqieguo.utils;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeatherAPI {
    public static Map getWeather(String param) throws Exception {
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        PrintWriter out = null;
        String url = "https://api.vvhan.com/api/weather";
        URL uri = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
        connection.setRequestMethod("POST");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(10000);
        connection.setRequestProperty("accept", "*/*");
        //发送参数
        connection.setDoOutput(true);
        out = new PrintWriter(connection.getOutputStream());
        out.print("city=" + "长沙");
        out.flush();
        //接收结果
        is = connection.getInputStream();
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        //缓冲逐行读取
        while ( ( line = br.readLine() ) != null )
        {
            sb.append(line);
        }
        String backStr=sb.toString();
        HashMap<String,Object> hashMap = JSONObject.parseObject(backStr, (Type) Map.class);
        //关闭流
        try
        {
            if(is!=null)
            {
                is.close();
            }
            if(br!=null)
            {
                br.close();
            }
            if (out!=null)
            {
                out.close();
            }
        } catch ( Exception ignored )
        {
            System.out.println(ignored);
        }
        return hashMap;
    }
}
