package com.hnucm.qushiyang.utils;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

public class BaiduMapApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        应用程序启动就会执行
//        初始化的事情
        //在使用SDK各组件之前初始化context信息，传入 ApplicationContext
        SDKInitializer.initialize(this);

        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型。
        // 包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
