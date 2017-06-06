package com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p;

import android.app.Activity;
import android.content.Context;

import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.v.IMyDeviceMapNavigationV;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/8.
 */

public class MyDeviceMapNavigationP implements IMyDeviceMapNavigationP {
    private IMyDeviceMapNavigationV mView;
    private Context mContext;
    public static List<Activity> activityList = new LinkedList<Activity>();

    public MyDeviceMapNavigationP(IMyDeviceMapNavigationV view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void startNavigation() {



    }

    @Override
    public void stopNavigation() {

    }
}
