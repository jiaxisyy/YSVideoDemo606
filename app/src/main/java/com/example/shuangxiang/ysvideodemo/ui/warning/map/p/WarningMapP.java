package com.example.shuangxiang.ysvideodemo.ui.warning.map.p;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.warning.map.v.IWarningMapV;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/10.
 */

public class WarningMapP implements IWarningMapP, BaiduMap.OnMarkerClickListener {
    private IWarningMapV mIWarningMapV;
    private TextureMapView mMapView;
    private int mSize;
    private Intent mIntent;
    private List<WarningInfo.ListBean> mList;
    private Context mContext;
    private BaiduMap mBaiduMap;

    public WarningMapP(IWarningMapV IWarningMapV, TextureMapView mapView, Context context) {
        mContext = context;
        mIWarningMapV = IWarningMapV;
        mMapView = mapView;
    }

    private static final int MAKERTYPE_RED = 1;
    private static final int MAKERTYPE_ORANGE = 2;
    private static final int MAKERTYPE_YELLOW = 3;
    private static final int MAKERTYPE_GREEN = 4;
    private BitmapDescriptor maker;

    @Override
    public void clickRed() {
        mBaiduMap.clear();
        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getLevel().equals("1")) {
                addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                        .getLongitude()), MAKERTYPE_RED);
            }
        }


    }

    @Override
    public void setRedNum(int num) {

    }

    @Override
    public void clickOrange() {
        mBaiduMap.clear();
        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getLevel().equals("2")) {
                addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                        .getLongitude()), MAKERTYPE_ORANGE);
            }
        }

    }

    @Override
    public void setOrangeNum(int num) {


    }

    @Override
    public void clickYellow() {
        mBaiduMap.clear();
        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getLevel().equals("3")) {
                addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                        .getLongitude()), MAKERTYPE_YELLOW);
            }
        }

    }

    @Override
    public void setYellowNum(int num) {

    }

    @Override
    public void clickGreen() {
        mBaiduMap.clear();
        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getLevel().equals("4")) {
                addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                        .getLongitude()), MAKERTYPE_GREEN);
            }
        }
    }

    @Override
    public void setGreenNum(int num) {

    }

    @Override
    public void clickAll() {
        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getLevel().equals("1")) {
                addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                        .getLongitude()), MAKERTYPE_RED);
            } else if (mList.get(i).getLevel().equals("2")) {
                addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                        .getLongitude()), MAKERTYPE_ORANGE);

            } else if (mList.get(i).getLevel().equals("3")) {
                addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                        .getLongitude()), MAKERTYPE_YELLOW);

            } else if (mList.get(i).getLevel().equals("4")) {
                addMaker(Double.valueOf(mList.get(i).getLatitude()), Double.valueOf(mList.get(i)
                        .getLongitude()), MAKERTYPE_GREEN);
            }

        }

    }

    @Override
    public void init() {
        mBaiduMap = mMapView.getMap();
        //注册标注的点击事件
        mBaiduMap.setOnMarkerClickListener(this);


    }

    @Override
    public void addMaker(double latitude, double lontitude, int makerType) {
        LatLng ll = new LatLng(latitude, lontitude);
        //构建Marker图标
        //TODO
        //相同标注图标没有重复利用,此处需要优化2017.5.8
        switch (makerType) {
            case MAKERTYPE_RED:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_warning_map_red);
                break;
            case MAKERTYPE_ORANGE:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_warning_map_orange);
                break;
            case MAKERTYPE_YELLOW:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_warning_map_yellow);
                break;
            case MAKERTYPE_GREEN:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_warning_map_green);
                break;
        }
        OverlayOptions mygps = new MarkerOptions()
                .position(ll)
                .icon(maker);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(mygps);

    }

    @Override
    public void setData(List<WarningInfo.ListBean> data) {
        mList = data;


        int countRed = 0;
        int countOrange = 0;
        int countYellow = 0;
        int countGreen = 0;
        mSize = data.size();
        for (int i = 0; i < mSize; i++) {
            if (data.get(i).getLevel().equals("1")) {
                if(!data.get(i).getLatitude().equals("")&&!data.get(i)
                        .getLongitude().equals("")){
                    addMaker(Double.valueOf(data.get(i).getLatitude()), Double.valueOf(data.get(i)
                            .getLongitude()), MAKERTYPE_RED);

                }
                countRed++;
            } else if (data.get(i).getLevel().equals("2")) {
                if(!data.get(i).getLatitude().equals("")&&!data.get(i)
                        .getLongitude().equals("")){
                    addMaker(Double.valueOf(data.get(i).getLatitude()), Double.valueOf(data.get(i)
                            .getLongitude()), MAKERTYPE_ORANGE);

                }
                countOrange++;
            } else if (data.get(i).getLevel().equals("3")) {
                if(!data.get(i).getLatitude().equals("")&&!data.get(i)
                        .getLongitude().equals("")){
                    addMaker(Double.valueOf(data.get(i).getLatitude()), Double.valueOf(data.get(i)
                            .getLongitude()), MAKERTYPE_YELLOW);

                }
                countYellow++;
            } else if (data.get(i).getLevel().equals("4")) {
                if(!data.get(i).getLatitude().equals("")&&!data.get(i)
                        .getLongitude().equals("")){
                    addMaker(Double.valueOf(data.get(i).getLatitude()), Double.valueOf(data.get(i)
                            .getLongitude()), MAKERTYPE_GREEN);

                }
                countGreen++;
            }
        }
        mIWarningMapV.setRedNum(countRed);
        mIWarningMapV.setOrangeNum(countOrange);
        mIWarningMapV.setYellowNum(countYellow);
        mIWarningMapV.setGreenNum(countGreen);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_infowindow, mMapView, false);
        TextView name = (TextView) view.findViewById(R.id.tv_dialog_mapInfoWindow_deviceName);
        final TextView status = (TextView) view.findViewById(R.id
                .tv_dialog_mapInfoWindow_deviceAddress);
        final double endLatitude = marker.getPosition().latitude;
        final double endLongitude = marker.getPosition().longitude;
        ImageView dataShow = (ImageView) view.findViewById(R.id.iv_dialog_mapInfoWindow_dataShow);
        ImageView navigation = (ImageView) view.findViewById(R.id.iv_dialog_mapInfoWindow_navigation);
        navigation.setVisibility(View.INVISIBLE);
        dataShow.setVisibility(View.INVISIBLE);
        for (int i = 0; i < mSize; i++) {
            if (mList.get(i).getLatitude().equals(String.valueOf(endLatitude)) && mList.get(i)
                    .getLongitude().equals(String.valueOf(endLongitude))) {
                String strName = mList.get(i).getName();
                String statusCode = mList.get(i).getStatus();
                name.setText("预警名称:  " + strName);
                if (statusCode.equals("1")) {
                    status.setText("预警状态:  报警结束");
                } else {
                    status.setText("预警状态:  报警");
                }

            }
        }
//        //导航
//        navigation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mIntent.putExtra("endLatitude", endLatitude);
//                mIntent.putExtra("endLongitude", endLongitude);
//                mContext.startActivity(mIntent);
//            }
//        });
        LatLng latLng = new LatLng(endLatitude, endLongitude);
        InfoWindow infoWindow = new InfoWindow(view, latLng, -100);
        mBaiduMap.showInfoWindow(infoWindow);
        return false;
    }
}
