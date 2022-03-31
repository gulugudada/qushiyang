package com.hnucm.qushiyang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.hnucm.qushiyang.fragment.Main_Fragment1;
import com.hnucm.qushiyang.fragment.Main_Fragment2;
import com.hnucm.qushiyang.fragment.Main_Fragment3;
import com.hnucm.qushiyang.fragment.Main_Fragment4;
import com.hnucm.qushiyang.utils.AddressInformation;

public class MainActivity extends AppCompatActivity {
    public LocationClient mLocationClient;
    Main_Fragment1 main_fragment1 = new Main_Fragment1();
    Main_Fragment2 main_fragment2 = new Main_Fragment2();
    Main_Fragment3 main_fragment3 = new Main_Fragment3();
    Main_Fragment4 main_fragment4 = new Main_Fragment4();
    RelativeLayout constraintLayout;
    RelativeLayout constraintLayout2;
    RelativeLayout constraintLayout3;
    RelativeLayout constraintLayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        requestLocation();

        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout3 = findViewById(R.id.constraintLayout3);
        constraintLayout4 = findViewById(R.id.constraintLayout4);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,main_fragment1).commit();
        constraintLayout.setSelected(true);
        constraintLayout2.setSelected(false);
        constraintLayout3.setSelected(false);
        constraintLayout4.setSelected(false);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,main_fragment1).commit();
                constraintLayout.setSelected(true);
                constraintLayout2.setSelected(false);
                constraintLayout3.setSelected(false);
                constraintLayout4.setSelected(false);
            }
        });
        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,main_fragment2).commit();
                constraintLayout.setSelected(false);
                constraintLayout2.setSelected(true);
                constraintLayout3.setSelected(false);
                constraintLayout4.setSelected(false);
            }
        });
        constraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,main_fragment3).commit();
                constraintLayout.setSelected(false);
                constraintLayout2.setSelected(false);
                constraintLayout3.setSelected(true);
                constraintLayout4.setSelected(false);
            }
        });
        constraintLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,main_fragment4).commit();
                constraintLayout.setSelected(false);
                constraintLayout2.setSelected(false);
                constraintLayout3.setSelected(false);
                constraintLayout4.setSelected(true);
            }
        });
    }

    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void  initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }

    //软件生命周期结束之后关闭定位
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mLocationClient.stop();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation location) {
            AddressInformation.longitude = location.getLongitude();
            AddressInformation.latitude = location.getLatitude();
            AddressInformation.locType = location.getLocType();
            AddressInformation.country = location.getCountry();
            AddressInformation.province = location.getProvince();
            AddressInformation.city = location.getCity();
            AddressInformation.district = location.getDistrict();
            AddressInformation.street = location.getStreet();
            AddressInformation.addrStr = location.getAddrStr();
        }
    }
}