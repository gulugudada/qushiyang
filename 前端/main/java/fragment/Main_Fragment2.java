package com.hnucm.qushiyang.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.hnucm.qushiyang.MainActivity;
import com.hnucm.qushiyang.PoiAdapter;
import com.hnucm.qushiyang.R;
import com.hnucm.qushiyang.utils.AddressInformation;

import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.SENSOR_SERVICE;


//public class Main_Fragment2 extends Fragment implements SensorEventListener {
//    private Activity mActivity;
//    private MapView mMapView;
//    private BaiduMap mBaiduMap;
//    private LocationClient mLocationClient;
//    private boolean isFirstLoc = true;
//    private Double lastX = 0.0;
//    private float mCurrentDirection = 0;
//    private double mCurrentLat = 0.0;
//    private double mCurrentLon = 0.0;
//    private MyLocationData myLocationData;
//    private float mCurrentAccracy;
//    private SensorManager mSensorManager;
//    private PoiSearch mPoiSearch = null;
//    private ImageView imageView;
public class Main_Fragment2 extends Fragment{
    View view;
    WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_2, container, false);
        webView = view.findViewById(R.id.webview);
        webView.loadUrl("https://ditu.amap.com/");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;

                try{
                    if(!url.startsWith("http://") && !url.startsWith("https://")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                }catch (Exception e){//防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }

                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = webView.getSettings();
//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //获取定位
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){

            //配置权限（同样在WebChromeClient中实现）
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

        });


        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        return view;
    }

    /**
     * 初始化View
     */
//    private void initView() {
//        mMapView = view.findViewById(R.id.bmapView);
//
//        mBaiduMap = mMapView.getMap();
//        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//        MyLocationConfiguration myLocationConfiguration =
//                new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
//        // 设置定位图层配置信息
//        mBaiduMap.setMyLocationConfiguration(myLocationConfiguration);
//        // 获取传感器管理服务
//        mSensorManager = (SensorManager) mActivity.getSystemService(SENSOR_SERVICE);
//        // 为系统的方向传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
//                SensorManager.SENSOR_DELAY_UI);
//    }
//
//    /**
//     * 启动定位
//     */
//    private void startLocation() {
//        // 定位初始化
//        mLocationClient = new LocationClient(mActivity);
//        mLocationClient.registerLocationListener(mListener);
//        LocationClientOption locationClientOption = new LocationClientOption();
//        // 可选，设置定位模式，默认高精度 LocationMode.Hight_Accuracy：高精度；
//        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        // 可选，设置返回经纬度坐标类型，默认GCJ02
//        locationClientOption.setCoorType("bd09ll");
//        // 如果设置为0，则代表单次定位，即仅定位一次，默认为0
//        // 如果设置非0，需设置1000ms以上才有效
//        locationClientOption.setScanSpan(1000);
//        //可选，设置是否使用gps，默认false
//        locationClientOption.setOpenGps(true);
//        // 可选，是否需要地址信息，默认为不需要，即参数为false
//        // 如果开发者需要获得当前点的地址信息，此处必须为true
//        locationClientOption.setIsNeedAddress(true);
//        // 可选，默认false，设置是否需要POI结果，可以在BDLocation
//        locationClientOption.setIsNeedLocationPoiList(true);
//        // 设置定位参数
//        mLocationClient.setLocOption(locationClientOption);
//        // 开启定位
//        mLocationClient.start();
//    }
//
//    /**
//     * 传感器方向信息回调
//     */
//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//        double x = sensorEvent.values[SensorManager.DATA_X];
//        if (Math.abs(x - lastX) > 1.0) {
//            mCurrentDirection = (float) x;
//            myLocationData = new MyLocationData.Builder()
//                    .accuracy(mCurrentAccracy)
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentDirection)
//                    .latitude(mCurrentLat)
//                    .longitude(mCurrentLon).build();
////            Log.i("sb",""+myLocationData.direction);
////            Log.i("sb",""+myLocationData.latitude);
////            Log.i("sb",""+myLocationData.longitude);
//            mBaiduMap.setMyLocationData(myLocationData);
//        }
//        lastX = x;
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }
//
//    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
//
//        /**
//         * 定位请求回调函数
//         *
//         * @param location 定位结果
//         */
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // MapView 销毁后不在处理新接收的位置
//            if (location == null || mMapView == null) {
//                return;
//            }
//            mCurrentLat = location.getLatitude();
//            mCurrentLon = location.getLongitude();
//            mCurrentAccracy = location.getRadius();
//            myLocationData = new MyLocationData.Builder()
//                    .accuracy(mCurrentAccracy)// 设置定位数据的精度信息，单位：米
//                    .direction(mCurrentDirection)// 此处设置开发者获取到的方向信息，顺时针0-360
//                    .latitude(mCurrentLat)
//                    .longitude(mCurrentLon)
//                    .build();
//            Log.i("sb",""+ location.getCity());
//            Log.i("sb",""+AddressInformation.latitude);
//            Log.i("sb",""+location.getLocType());
//            mBaiduMap.setMyLocationData(myLocationData);
//            if (location.getLocType() == BDLocation.TypeGpsLocation
//                    || location.getLocType() == BDLocation.TypeNetWorkLocation
//                    || location.getLocType() == BDLocation.TypeOffLineLocation) {
//                if (isFirstLoc) {
//                    isFirstLoc = false;
//                    LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
//                    MapStatus.Builder builder = new MapStatus.Builder();
//                    builder.target(ll).zoom(18.0f);
//                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                    imageView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            builder.target(ll).zoom(18.0f);
//                            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//                        }
//                    });
//                }
//            }
//        }
//    };
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.mActivity = (Activity)context;
//    }
}