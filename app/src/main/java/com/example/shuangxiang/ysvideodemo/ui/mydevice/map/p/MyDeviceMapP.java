package com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.example.shuangxiang.ysvideodemo.MyLocationListener;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.ui.SecondHomeActivity;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.navigation.BNDemoMainActivity;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.v.IMyDeviceMapV;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by shuang.xiang on 2017/5/4.
 */

public class MyDeviceMapP implements IMydeviceMapP, BDLocationListener, BaiduMap
        .OnMarkerClickListener {


    private IMyDeviceMapV mView;
    private Context mContext;
    private LocationClient mLocationClient;
    private static final int MAKERTYPE_MYSELF = 0;
    private static final int MAKERTYPE_ON = -1;
    private static final int MAKERTYPE_OFF = 1;
    public BDLocationListener myListener = new MyLocationListener();
    private TextureMapView mMapView;
    private BaiduMap mBaiduMap;
    private List<MyDeviceInfo.ListBean> mList;
    private BitmapDescriptor maker = null;
    private int mSize;
    private Intent mIntent;
    private double mStartLatitude;
    private double mStartLongitude;
    private boolean mFirstInto = true;
    private String mId;
    private Disposable mDisposable;
    private String mDataTemplateId;
    private String mStrName;
    private CompositeDisposable cd = new CompositeDisposable();


    public MyDeviceMapP(IMyDeviceMapV view, Context context, TextureMapView mapView) {
        mView = view;
        mContext = context;
        this.mMapView = mapView;
        mLocationClient = new LocationClient(mContext);
    }

    @Override
    public void clickOn() {
        mBaiduMap.clear();

        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getOnlineStatus().equals("ONLINE")) {
                if (!mList.get(i).getLatitude().equals("") && !mList.get(i)
                        .getLongitude().equals("")) {
                    addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                            .getLongitude()), MAKERTYPE_ON);
                }

            }
        }


    }

    @Override
    public void clickOff() {
        mBaiduMap.clear();
        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getOnlineStatus().equals("OFFLINE")) {
                if (!mList.get(i).getLatitude().equals("") && !mList.get(i)
                        .getLongitude().equals("")) {
                    addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                            .getLongitude()), MAKERTYPE_OFF);
                }


            }
        }
    }

    @Override
    public void clickAll() {
        if (mBaiduMap != null) {
            mBaiduMap.clear();
        }
        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getOnlineStatus().equals("ONLINE")) {
                if (!mList.get(i).getLatitude().equals("") && !mList.get(i)
                        .getLongitude().equals("")) {
                    addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                            .getLongitude()), MAKERTYPE_ON);
                }

            } else {
                if (!mList.get(i).getLatitude().equals("") && !mList.get(i)
                        .getLongitude().equals("")) {
                    addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                            .getLongitude()), MAKERTYPE_OFF);
                }

            }
        }


    }

    @Override
    public void addMaker(double latitude, double lontitude, int makerType) {
        LatLng ll = new LatLng(latitude, lontitude);
        //构建Marker图标
        //TODO
        //相同标注图标没有重复利用,此处需要优化2017.5.8
        switch (makerType) {
            case MAKERTYPE_MYSELF:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_earlywarning_map_positioning);
                break;
            case MAKERTYPE_ON:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_mydevice_online_green);
                break;
            case MAKERTYPE_OFF:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_mydevice_offline_grey);
                break;
        }
        OverlayOptions mygps = new MarkerOptions()
                .position(ll)
                .icon(maker);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(mygps);
        if (makerType == MAKERTYPE_MYSELF) {
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }


    @Override
    public void initBaiDuMap(List<MyDeviceInfo.ListBean> list) {
        Log.d("TEST", "initBaiDuMap");
        mList = new ArrayList<>();
        mList.addAll(list);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.clear();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient.registerLocationListener(this);
        initLocation();
        mLocationClient.start();
        mBaiduMap.setOnMarkerClickListener(this);
        getData();
        if (mFirstInto = true) {
            clickAll();
            mFirstInto = false;
        }
    }

    private void getData() {
        if (mList != null && mList.size() > 0) {
            String name = mList.get(0).getName();
            mSize = mList.size();
            Log.d("TEST", "MyDeviceMapP->clickAll->name->" + name);
            Log.d("TEST", "MyDeviceMapP->clickAll->" + mList.get(0).getLatitude());
            for (int i = 0; i < mSize; i++) {
                if (mList.get(i).getOnlineStatus().equals("ONLINE")) {
                    if (!mList.get(i).getLatitude().equals("") && !mList.get(i)
                            .getLongitude().equals("")) {
                        addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                                .getLongitude()), MAKERTYPE_ON);
                    }
                } else {
                    if (!mList.get(i).getLatitude().equals("") && !mList.get(i)
                            .getLongitude().equals("")) {
                        addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                                .getLongitude()), MAKERTYPE_OFF);
                    }
                }
            }
        }

    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {

        Log.d("TEST", "MyDeviceMapP->clickAll->size->onReceiveLocation");
        mStartLatitude = bdLocation.getLatitude();
        mStartLongitude = bdLocation.getLongitude();
        mLocationClient.stop();
        Log.d("TEST", "MyDeviceMapP->clickAll->size->onReceiveLocation->stop");
        addMaker(mStartLatitude, mStartLongitude, MAKERTYPE_MYSELF);

    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);
//        可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的


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
    public boolean onMarkerClick(final Marker marker) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_infowindow, mMapView, false);
        TextView name = (TextView) view.findViewById(R.id.tv_dialog_mapInfoWindow_deviceName);
        final TextView address = (TextView) view.findViewById(R.id.tv_dialog_mapInfoWindow_deviceAddress);
        final double endLatitude = marker.getPosition().latitude;
        final double endLongitude = marker.getPosition().longitude;
        ImageView dataShow = (ImageView) view.findViewById(R.id.iv_dialog_mapInfoWindow_dataShow);
        ImageView navigation = (ImageView) view.findViewById(R.id.iv_dialog_mapInfoWindow_navigation);
        mIntent = new Intent(mContext, BNDemoMainActivity.class);

        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getLatitude().equals(String.valueOf(endLatitude)) && mList.get(i)
                    .getLongitude().equals(String.valueOf(endLongitude))) {
                mStrName = mList.get(i).getName();
                name.setText("设备名称:  " + mStrName);
                address.setText("设备地址:  " + mList.get(i).getAddr());
                mId = mList.get(i).getId();
                mDataTemplateId = mList.get(i).getDataTemplateId();
                mIntent.putExtra("name", mStrName);

                Log.d("TEST", "MyDeviceMapP->id=" + mId);
                Log.d("TEST", "MyDeviceMapP->dataTemplateId=" + mDataTemplateId);
            }
        }
        //导航
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIntent.putExtra("endLatitude", endLatitude);
                mIntent.putExtra("endLongitude", endLongitude);
                mIntent.putExtra("startLatitude", mStartLatitude);
                mIntent.putExtra("startLongitude", mStartLongitude);
                mContext.startActivity(mIntent);
            }
        });
        dataShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SecondHomeActivity.class);
                intent.putExtra("flag", "monitoring");
                CacheUtils.putString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_ID, mId);
                CacheUtils.putString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_DATATEMPLATEID, mDataTemplateId);
                CacheUtils.putString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_TBTITLE, mStrName);
                mContext.startActivity(intent);
            }
        });
        LatLng latLng = new LatLng(endLatitude, endLongitude);
        if (mStartLatitude == endLatitude && mStartLongitude == endLongitude) {

        } else {
            InfoWindow infoWindow = new InfoWindow(view, latLng, -100);
            mBaiduMap.showInfoWindow(infoWindow);
        }
        return false;
    }


}
