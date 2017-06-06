package com.example.shuangxiang.ysvideodemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.shuangxiang.ysvideodemo.MyLocationListener;
import com.example.shuangxiang.ysvideodemo.R;

import java.util.ArrayList;

/**
 * Created by shuang.xiang on 2017/3/9.
 */

public class BaiDuMapActivity extends Activity implements BDLocationListener {

    MapView mMapView = null;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private boolean flag = true;//判断是否第一次定位


    public void onMyCreate() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
//TODO


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidumap);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        addMaker(22.528859, 113.945747, true);
        startGps();
//        gpsMyself();
    }

    private void gpsMyself() {
        mBaiduMap = mMapView.getMap();
// 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
// 定位初始化
        mLocationClient = new LocationClient(BaiDuMapActivity.this);
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        mLocationClient.setLocOption(option);
        mLocationClient.start();

        LatLng ll = new LatLng(22.528859,
                113.945747);

        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.user);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions mygps = new MarkerOptions()
                .position(ll)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(mygps);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }


    /**
     * 定位
     */
    private void startGps() {
        onMyCreate();
        initLocation();
        mLocationClient.start();

    }

    private void getGps() {


    }


    /**
     * 添加地图标注
     */
    private void addMaker(double latitude, double lontitude, boolean isFirst) {
        //定义Maker坐标点
        final LatLng point = new LatLng(latitude, lontitude);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.green);
        BitmapDescriptor bitmap2 = BitmapDescriptorFactory
                .fromResource(R.drawable.user);
//        BitmapDescriptor bitmap2 = BitmapDescriptorFactory
//                .fromResource(R.drawable.z_17);
//        BitmapDescriptor bitmap3 = BitmapDescriptorFactory
//                .fromResource(R.drawable.z_18);
//        BitmapDescriptor bitmap4 = BitmapDescriptorFactory
//                .fromResource(R.drawable.z_19);
//        BitmapDescriptor bitmap5 = BitmapDescriptorFactory
//                .fromResource(R.drawable.z_20);


////构建MarkerOption，用于在地图上添加Marker
//        OverlayOptions option = new MarkerOptions()
//                .position(point)
//                .icon(bitmap);
//在地图上添加Marker，并显示
//        mBaiduMap.addOverlay(option);
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(bitmap);
        giflist.add(bitmap2);
//        giflist.add(bitmap3);
//        giflist.add(bitmap4);
//        giflist.add(bitmap5);


        OverlayOptions ooD = new MarkerOptions().position(point).icons(giflist)
                .zIndex(0).period(10);

        mBaiduMap.addOverlay(ooD);

        if (isFirst) {//第一次定位
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(point).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        } else {
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(point);
        }


        //获取到点击标注的点击事件
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Toast.makeText(BaiDuMapActivity.this, latLng.latitude + "", Toast.LENGTH_SHORT).show();

            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });


    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要


        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        mBaiduMap.clear();
        double latitude = bdLocation.getLatitude();
        double lontitude = bdLocation.getLongitude();
        Log.d("TAG", latitude + "===" + lontitude);
        mLocationClient.stop();
        if (flag) {// 第一次定位
            addMaker(latitude, lontitude, true);//第一次
            flag = false;
        } else {
            addMaker(latitude, lontitude, false);//不是第一次
        }


    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
