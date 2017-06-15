package com.example.shuangxiang.ysvideodemo.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.login.view.LoginActivity;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.videogo.openapi.EZOpenSDK;

import java.lang.ref.WeakReference;

/**
 * Created by shuang.xiang on 2017/4/17.
 */

public class StartActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int MY_PERMISSIONS_REQUEST = 0;
    //摄像头key
    private static String APP_KEY = "fd46e0ca69864ce1a8b1d948698aee3e";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        //当页面更布局有背景时使用可以全屏观看
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activiti_start);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("TEST","PERMISSION_GRANTED");
                //萤石云初始化,必须申请权限,我曹他妈的不说明白
                EZOpenSDK.initLib(getApplication(), APP_KEY, "");
            }

        }
    }

    @Override
    protected void initSomething() {
//        PermissionUtils.requestPermission(this, PermissionUtils.CODE_READ_PHONE_STATE,
//                mPermissionGrant);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                MY_PERMISSIONS_REQUEST);
        ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));
        final boolean isFirst = CacheUtils.getBoolean(StartActivity.this, Constants.Define.FIRST_START, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Constants.Define.START_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isFirst) {
                    startActivity(new Intent(StartActivity.this, StartPagerActivity.class));
                } else {
                    startActivity(new Intent(StartActivity.this, LoginActivity.class));
                }
                finish();
            }
        }).start();
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults,
//                mPermissionGrant);
//    }
//    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
//        @Override
//        public void onPermissionGranted(int requestCode) {
//            switch (requestCode) {
//                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
//
//                    break;
//                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
//
//                    break;
//            }
//        }
//    };

}
