package com.nengguoqieguo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * 获取验证码的类
 */
public class SMS {

    public static String getSMS(String tel) throws Exception {
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        PrintWriter out = null;
        String yanzhengma = "";
//        String account = "C78661455";
//        String password = "46c54ff77acdafaddbc56c5442283e93";
        Random random = new Random();
        for(int i = 0;i<6;i++){
            yanzhengma += random.nextInt(10);
        }
        String url = "https://106.ihuyi.com/webservice/sms.php?method=Submit&account=C78661455&password=46c54ff77acdafaddbc56c5442283e93&mobile=" + tel + "&content=%E6%82%A8%E7%9A%84%E9%AA%8C%E8%AF%81%E7%A0%81%E6%98%AF%EF%BC%9A" + yanzhengma + "%E3%80%82%E8%AF%B7%E4%B8%8D%E8%A6%81%E6%8A%8A%E9%AA%8C%E8%AF%81%E7%A0%81%E6%B3%84%E9%9C%B2%E7%BB%99%E5%85%B6%E4%BB%96%E4%BA%BA%E3%80%82&format=JSON";
        System.out.println(url);
        URL uri = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
        connection.setRequestMethod("POST");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(10000);
        connection.setRequestProperty("accept", "*/*");
        //发送参数
        connection.setDoOutput(true);
        out = new PrintWriter(connection.getOutputStream());
//        out.print("method=Submit");
//        out.print("account=" + account);
//        out.print("password=" + password);
//        out.print("mobile=" + "19974712583");
//        out.print("content=您的验证码是：" + yanzhengma + "。请不要把验证码泄露给其他人。");
//        out.print("format=JSON");
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
        System.out.println(backStr);
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
        return yanzhengma;
    }
}
