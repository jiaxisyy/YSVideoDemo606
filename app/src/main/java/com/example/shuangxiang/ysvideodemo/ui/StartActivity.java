package com.example.shuangxiang.ysvideodemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.login.view.LoginActivity;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;

import java.lang.ref.WeakReference;

/**
 * Created by shuang.xiang on 2017/4/17.
 */

public class StartActivity extends BaseActivity {
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
    protected void initSomething() {
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


}
