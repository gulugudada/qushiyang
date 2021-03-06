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
                }catch (Exception e){//??????crash (???????????????????????????????????????scheme?????????url???APP, ?????????crash)
                    return true;//???????????????app????????????true?????????????????????????????????????????????????????????????????????????????????
                }

                // TODO Auto-generated method stub
                //????????????true??????????????????WebView????????????false??????????????????????????????????????????
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings = webView.getSettings();
//??????????????????????????????Javascript????????????webview??????????????????Javascript
        webSettings.setJavaScriptEnabled(true);

//????????????????????????????????????
        webSettings.setUseWideViewPort(true); //????????????????????????webview?????????
        webSettings.setLoadWithOverviewMode(true); // ????????????????????????
//???????????????
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true); // ????????????????????????

//????????????
        webSettings.setSupportZoom(true); //????????????????????????true??????????????????????????????
        webSettings.setBuiltInZoomControls(true); //????????????????????????????????????false?????????WebView????????????
        webSettings.setDisplayZoomControls(false); //???????????????????????????

//??????????????????
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //??????webview?????????
        webSettings.setAllowFileAccess(true); //????????????????????????
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //????????????JS???????????????
        webSettings.setLoadsImagesAutomatically(true); //????????????????????????
        webSettings.setDefaultTextEncodingName("utf-8");//??????????????????

        //????????????
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){

            //????????????????????????WebChromeClient????????????
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
     * ?????????View
     */
//    private void initView() {
//        mMapView = view.findViewById(R.id.bmapView);
//
//        mBaiduMap = mMapView.getMap();
//        // ??????????????????
//        mBaiduMap.setMyLocationEnabled(true);
//        MyLocationConfiguration myLocationConfiguration =
//                new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
//        // ??????????????????????????????
//        mBaiduMap.setMyLocationConfiguration(myLocationConfiguration);
//        // ???????????????????????????
//        mSensorManager = (SensorManager) mActivity.getSystemService(SENSOR_SERVICE);
//        // ??????????????????????????????????????????
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
//                SensorManager.SENSOR_DELAY_UI);
//    }
//
//    /**
//     * ????????????
//     */
//    private void startLocation() {
//        // ???????????????
//        mLocationClient = new LocationClient(mActivity);
//        mLocationClient.registerLocationListener(mListener);
//        LocationClientOption locationClientOption = new LocationClientOption();
//        // ????????????????????????????????????????????? LocationMode.Hight_Accuracy???????????????
//        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        // ???????????????????????????????????????????????????GCJ02
//        locationClientOption.setCoorType("bd09ll");
//        // ???????????????0?????????????????????????????????????????????????????????0
//        // ???????????????0????????????1000ms???????????????
//        locationClientOption.setScanSpan(1000);
//        //???????????????????????????gps?????????false
//        locationClientOption.setOpenGps(true);
//        // ?????????????????????????????????????????????????????????????????????false
//        // ?????????????????????????????????????????????????????????????????????true
//        locationClientOption.setIsNeedAddress(true);
//        // ???????????????false?????????????????????POI??????????????????BDLocation
//        locationClientOption.setIsNeedLocationPoiList(true);
//        // ??????????????????
//        mLocationClient.setLocOption(locationClientOption);
//        // ????????????
//        mLocationClient.start();
//    }
//
//    /**
//     * ???????????????????????????
//     */
//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//        double x = sensorEvent.values[SensorManager.DATA_X];
//        if (Math.abs(x - lastX) > 1.0) {
//            mCurrentDirection = (float) x;
//            myLocationData = new MyLocationData.Builder()
//                    .accuracy(mCurrentAccracy)
//                    // ?????????????????????????????????????????????????????????0-360
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
//         * ????????????????????????
//         *
//         * @param location ????????????
//         */
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // MapView ???????????????????????????????????????
//            if (location == null || mMapView == null) {
//                return;
//            }
//            mCurrentLat = location.getLatitude();
//            mCurrentLon = location.getLongitude();
//            mCurrentAccracy = location.getRadius();
//            myLocationData = new MyLocationData.Builder()
//                    .accuracy(mCurrentAccracy)// ????????????????????????????????????????????????
//                    .direction(mCurrentDirection)// ?????????????????????????????????????????????????????????0-360
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