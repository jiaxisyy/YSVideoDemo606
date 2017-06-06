package com.example.shuangxiang.ysvideodemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.login.view.LoginActivity;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shuang.xiang on 2017/4/17.
 */

public class StartPagerActivity extends BaseActivity {
    @BindView(R.id.vp_startPager)
    ViewPager mVpStartPager;
    private List<View> mViewList = new ArrayList<>();
    private Button mBtn_experience;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start_pager);
    }

    @Override
    protected void initSomething() {
        ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));

        LayoutInflater inflater = LayoutInflater.from(this);
        //获取四个view
        View view1 = inflater.inflate(R.layout.start_pager1, null);
        View view2 = inflater.inflate(R.layout.start_pager2, null);
        View view3 = inflater.inflate(R.layout.start_pager3, null);

        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mBtn_experience = (Button) view3.findViewById(R.id.btn_startPager_experience);

        mVpStartPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView(mViewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mViewList.get(position));
                return mViewList.get(position);
            }
        });

        toNextActivity();

    }

    private void toNextActivity() {
        mBtn_experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartPagerActivity.this, LoginActivity.class));
                CacheUtils.putBoolean(StartPagerActivity.this, Constants.Define.FIRST_START, false);
                finish();
            }
        });
    }


}
