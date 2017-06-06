package com.example.shuangxiang.ysvideodemo.ui.warning;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;


/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningActivity extends BaseActivity {
    @BindView(R.id.tb_warning)
    Toolbar mTbWarning;
    @BindView(R.id.tbl_warning)
    TabLayout mTabWarning;

    @BindView(R.id.tv_warning_title)
    TextView mTitle;
    private List<String> mTb_titles;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_warning);
    }

    @Override
    protected void initSomething() {
        ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));
        setImmerseLayout(mTbWarning);
        mTbWarning.setTitle("");
        mTbWarning.setNavigationIcon(R.drawable.icon_back);
        setSupportActionBar(mTbWarning);
        mTb_titles = new ArrayList<>();
        //添加标题
        mTb_titles.add(Constants.Define.WARNINGMAP.toString());
        mTb_titles.add(Constants.Define.WARNINGRECORD.toString());

        mTabWarning.setLayoutMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTb_titles.size(); i++) {
            mTabWarning.addTab(mTabWarning.newTab().setText(mTb_titles.get(i)));
        }
        String id = CacheUtils.getString(this, Constants.Define.MYDEVICE_TO_SECONDHOME_ID);
        String title = CacheUtils.getString(this, Constants.Define.MYDEVICE_TO_SECONDHOME_TBTITLE);
        if (id != null && !id.equals("")) {
            mTitle.setText(title);
        }
//        mTabWarning.setupWithViewPager(mViewPager);
        initView();
    }

    private void initView() {
//        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
//        myViewPagerAdapter.addFragment(WarningMapFragment.getInstance(), Constants.Define.WARNINGMAP);
//        myViewPagerAdapter.addFragment(WarningRecordFragment.getInstance(), Constants.Define.WARNINGRECORD);
//        mViewPager.setAdapter(myViewPagerAdapter);
        Utils.replace(getSupportFragmentManager(), R.id.fl_warning,
                WarningMapFragment.class);

        mTabWarning.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        Utils.replace(getSupportFragmentManager(), R.id.fl_warning,
                                WarningMapFragment.class);
                        break;
                    case 1:
                        Utils.replace(getSupportFragmentManager(), R.id.fl_warning,
                                WarningRecordFragment.class);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    protected void setImmerseLayout(View view) {
        //先将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //获取状态栏的高度
            int statusBarHeight = getStatusBarHeight(this);
            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;

    }
}
