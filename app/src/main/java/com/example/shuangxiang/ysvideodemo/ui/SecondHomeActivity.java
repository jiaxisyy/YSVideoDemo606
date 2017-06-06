package com.example.shuangxiang.ysvideodemo.ui;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.example.shuangxiang.ysvideodemo.ui.data.DataShowFragment;
import com.example.shuangxiang.ysvideodemo.ui.monitoring.MonitoringFragment;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.ParameterFragment;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * Created by shuang.xiang on 2017/5/2.
 */

public class SecondHomeActivity extends BaseActivity {
    @BindView(R.id.bnv_home2)
    BottomNavigationView mBnv;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home_second);

    }

    @Override
    protected void initSomething() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//解决视频界面闪屏
        ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.home_bottom_normal),
                getResources().getColor(R.color.home_bottom_checked)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        mBnv.setItemTextColor(csl);
        mBnv.setItemIconTintList(csl);

        setDefaultFragment();
        mBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnv_home2_data:
                        Utils.replace(getSupportFragmentManager(), R.id.fl_home2,
                                DataShowFragment.class);
                        break;
                    case R.id.bnv_home2_monitoring:
                        Utils.replace(getSupportFragmentManager(), R.id.fl_home2,
                                MonitoringFragment.class);
                        break;
                    case R.id.bnv_home2_setting:
                        Utils.replace(getSupportFragmentManager(), R.id.fl_home2,
                                ParameterFragment.class);
                        break;

                }
                return true;
            }
        });

    }

    private void setDefaultFragment() {

        Utils.replace(getSupportFragmentManager(), R.id.fl_home2,
                DataShowFragment.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;

    }

}
