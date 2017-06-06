package com.example.shuangxiang.ysvideodemo;

import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;
import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.videogo.openapi.EZOpenSDK;

/**
 * Created by shuang.xiang on 2017/2/14.
 */

public class MyApplication extends MultiDexApplication {
    //摄像头key
    private static String APP_KEY = "fd46e0ca69864ce1a8b1d948698aee3e";

    @Override
    public void onCreate() {
        super.onCreate();
        EZOpenSDK.initLib(this, APP_KEY, "");
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        new ApiManager(getApplicationContext()).initApiManager();

    }

}
